document.addEventListener("DOMContentLoaded", () => {
    const hostBtn = document.getElementById('hostRoomButton');
    const joinBtn = document.getElementById('joinRoomButton');
    const toggleJoinBtn = document.getElementById('toggleJoinButton');
    
    hostBtn.addEventListener('click', hostRoom);
    joinBtn.addEventListener('click', joinRoom);
    toggleJoinBtn.addEventListener('click', toggleJoinForm);

    const copyRoomCodeButton = document.getElementById('copyRoomCodeButton');
    const sendMessageButton = document.getElementById('sendMessageButton');

    copyRoomCodeButton.addEventListener('click', copyRoomCode);
    sendMessageButton.addEventListener('click', sendMessage);
  });
  
  let stompClient = null;
  let currentRoom = null;
  
  const sanitizeInput = (input) => {
    const div = document.createElement("div");
    div.innerText = input;
    return div.innerHTML;
  };
  
  const toggleJoinForm = () => {
    const joinForm = document.getElementById('join-room-form');
    joinForm.classList.toggle('hidden');
  };
  
  const hostRoom = async () => {
    const username = document.getElementById('username').value.trim();
    if (!username) {
      displayError('Please enter your name.');
      return;
    }
    try {
    const response = await fetch('/api/roomcode');
    if (!response.ok) {
      throw new Error('Failed to fetch room code.');
    }
    const code = await response.text();
    currentRoom = code;
    document.getElementById('room-id').innerText = currentRoom;
    initializeChat(username);
  } catch (error) {
    displayError('Error generating room code: ' + error.message);
  }
  };
  
  const joinRoom = () => {
    const username = document.getElementById('username').value.trim();
    if (!username) {
      displayError('Please enter your name.');
      return;
    }
    const roomCode = document.getElementById('roomCodeInput').value.trim().toUpperCase();
    if (!roomCode) {
      displayError('Please enter a valid room code.');
      return;
    }
    currentRoom = roomCode;
    document.getElementById('room-id').innerText = currentRoom;
    initializeChat(username);
  };
  
  const initializeChat = (username) => {
    document.getElementById('room-selection').classList.add('hidden');
    document.getElementById('chat-room').classList.remove('hidden');
  
    // Show connecting status
    displayStatus('Connecting to chat server...');
  
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
      displayStatus('Connected');
      stompClient.subscribe(`/topic/${currentRoom}`, (messageOutput) => {
        displayMessage(JSON.parse(messageOutput.body));
      });
      stompClient.send(`/app/chat.addUser/${currentRoom}`, {}, JSON.stringify({
        sender: username,
        type: 'JOIN',
        roomCode: currentRoom
      }));
    }, (error) => {
      console.error('STOMP error: ', error);
      displayError('Could not connect to WebSocket server. Please refresh and try again.');
    });
  };
  
  const sendMessage = () => {
    const messageInput = document.getElementById('message');
    const messageContent = sanitizeInput(messageInput.value.trim());
    const username = document.getElementById('username').value.trim();
    if (messageContent && stompClient) {
      const chatMessage = {
        sender: username,
        content: messageContent,
        type: 'CHAT',
        roomCode: currentRoom
      };
      stompClient.send(`/app/chat.sendMessage/${currentRoom}`, {}, JSON.stringify(chatMessage));
      messageInput.value = '';
    }
  };
  
  const displayMessage = (message) => {
    const conversation = document.getElementById('conversation');
    const messageElement = document.createElement('div');
    messageElement.classList.add('mb-2', 'p-2', 'rounded-md', 'max-w-xs', 'shadow');
  
    if (message.type === 'JOIN') {
      messageElement.classList.add('bg-green-100', 'text-green-800');
      messageElement.innerText = `${message.sender} joined the room.`;
    } else if (message.type === 'LEAVE') {
      messageElement.classList.add('bg-red-100', 'text-red-800');
      messageElement.innerText = `${message.sender} left the room.`;
    } else {
      messageElement.classList.add('bg-blue-100', 'text-blue-800');
      messageElement.innerText = `${message.sender}: ${message.content}`;
    }
    conversation.appendChild(messageElement);
    conversation.scrollTop = conversation.scrollHeight;
  };
  
  const copyRoomCode = () => {
    const code = document.getElementById('room-id').innerText;
    if (navigator.clipboard) {
      navigator.clipboard.writeText(code)
        .then(() => displayStatus(`Room Code copied: ${code}`))
        .catch(() => displayError('Failed to copy room code.'));
    } else {
      displayError('Clipboard API not supported.');
    }
  };
  
  const displayError = (msg) => {
    // Assume you have an error message div in your HTML
    const errorDiv = document.getElementById('error-message');
    if (errorDiv) {
      errorDiv.innerText = msg;
      errorDiv.classList.remove('hidden');
    } else {
      alert(msg);
    }
  };
  
  const displayStatus = (msg) => {
    console.log(msg);
  };
  