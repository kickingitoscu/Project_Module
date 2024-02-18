<template>
    <div v-if="showPopup">
      <Popup @toggle-popup="togglePopup" :popupText="popupText"/>
    </div>
    <DropFile ref="DropFile"></DropFile>
    <input v-model="headerText" placeholder="Enter suitable header">
    <input v-model="subheaderText" placeholder="Enter suitable subheader">
    <button @click="onSubmit">Create posts</button>
</template>
  
<script>
  import DropFile from '@/components/DropFile.vue';
  import Popup from '@/components/PopupWindow.vue'
  
  export default {
    name: 'HomeView',
    components: {
      DropFile,
      Popup
    },
    data() {
      return {
        headerText: "",
        subheaderText: "",
        showPopup: false,
        popupText: ''
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

        if (data?.msg) {
          this.enablePopup(data.msg);
          return;
        }
        await this.$router.push(`/posts/${data.id}`);
      },
      async togglePopup() {
        this.popupText = '';
        this.showPopup = false;
      },
      enablePopup(msg) {
        this.popupText = msg;
        this.showPopup = true;
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
  