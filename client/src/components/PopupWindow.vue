<template>
    <div class="popup">
      <div class="popup-content">
        <p>{{ popupText }}</p>
        <button @click="$emit('toggle-popup')">Close</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PopupWindow',
    props: {
      popupText: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .popup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .popup-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
  }
  
  button {
    margin-top: 10px;
  }
  </style>