<template>
  <div class="popup">
    <div class="popup-content">
      <p>{{ popupText }}</p>
      <div class="beyond-button">
        <button @click="$emit('toggle-popup')">Close</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PopupWindow",
  props: {
    popupText: {
      type: String,
      required: true,
    },
  },
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
  padding: 30px;
  border-radius: 8px;
  font-size: 20px;
}

.beyond-button {
  padding-top: 20px;
}

button {
  height: 30px;
  width: 100px;/* Adjust width as needed */
  font-size: 18px;
  background-color: #EE6A1F;
  border: 2px solid #EE6A1F; /* Set border color */
  color: white;
}

button:hover {
  background-color: #ad511c; /* color on hover */
  border: 2px solid #ad511c;
}

* {
  font-family: 'Open Sans', Arial, sans-serif;
}
</style>
