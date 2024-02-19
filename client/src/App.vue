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
  #header {
    background-color: #666666;
    padding: 1.5% 1.5% 1.5% 4%;
    margin: 0;
    
    display: flex;
    align-items: center;
    position: relative; 
  }

  .small-image {
    position: absolute;
    top: 50%;
    left: 0.275%; 
    transform: translateY(-50%);
    height: calc(100% - 15%); 
    width: auto;
  }

  .header-text {
    text-align: center;
    color: white;
    flex-grow: 1;
    font-size: 32px; 
  }

  * {
    font-family: 'Open Sans', Arial, sans-serif;
  }
</style>
