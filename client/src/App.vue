<template>
  <div>
    <div id="header">
      <img src="./assets/thws_logo.jpg" alt="Small Image" class="small-image">
      <div class="header-text">Instragram Post Generator</div>
    </div>
    <router-view></router-view>
  </div>
  
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
