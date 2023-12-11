<template>
  <DropFile ref="DropFile"></DropFile>
  <input v-model="headerText" placeholder="Enter suitable header">
  <input v-model="subheaderText" placeholder="Enter suitable subheader">
  <button @click="onSubmit">Create posts</button>
</template>

<script>
import DropFile from './components/DropFile.vue';

export default {
  name: 'App',
  components: {
    DropFile,
},
data() {
  return {
    headerText: "",
    subheaderText: ""
  }
},
methods: {
  async onSubmit() {
        // Create a new FormData object
    const formData = new FormData();

    // Append images to the FormData object
    for (let i = 0; i < this.$refs.DropFile.files.length; i++) {
        formData.append('images', this.$refs.DropFile.files[i]);
    }
    // Append strings to the FormData object
    formData.append('header', this.headerText);
    formData.append('subheader', this.subheaderText);
    console.debug(formData);
    const response = await fetch("api/uploadFiles", {
      method: "POST",
      body: formData,
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
