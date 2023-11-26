<template>
  <DropFile></DropFile>
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
  onSubmit() {
    console.debug('method called');
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
