<template>
  <DropFile ref="DropFile"></DropFile>
  <button @click="onSubmit">Create posts</button>
</template>

<script>
import DropFile from './components/DropFile.vue';

export default {
  name: 'App',
  components: {
    DropFile,
},
methods: {
  async onSubmit() {
    const response = await fetch("api/uploadFiles", {
      method: "POST",
      headers: {
        "Content-Type": "multipart/form-data",
      },
      body: this.$refs.DropFile.files,
    });
    const data = await response.json();
    console.debug('func called');
    console.log(data);
  }
  
}
}
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
