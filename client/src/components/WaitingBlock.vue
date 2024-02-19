<template>
  <div class="popup">
    <div class="popup-content">Please wait...</div>
  </div>
</template>

<script></script>

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
  font-size: 20px;
}

* {
  font-family: 'Open Sans', Arial, sans-serif;
}
</style>
