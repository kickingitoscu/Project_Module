<template>
  <div>
    <div v-if="showPopup">
      <Popup @toggle-popup="togglePopup" :popupText="popupText" />
    </div>
    <div v-if="isInWaitMode">
      <WaitingBlock />
    </div>
    <div class="container">
      <div class="centered-text">
        Upload your images and create Instagram posts
      </div>
      <div class="grid-container">
        <div class="column">
          <div class="drag-drop">
            <DropFile @on-error="enablePopup" ref="DropFile"></DropFile>
          </div>
        </div>
        <div class="column">
          <div class="input-block">
            <div class="input-row">
              <label for="headerInput">Header:</label>
              <input id="headerInput" class="input-field" v-model="headerText" placeholder="Enter suitable header" />
            </div>
            <div class="input-row">
              <label for="subheaderInput">Subheader:</label>
              <input id="subheaderInput" class="input-field" v-model="subheaderText" placeholder="Enter suitable subheader" />
            </div>
            <div class="input-row button-outer">
              <button class="button" @click="onSubmit" :disabled="buttonDisabled">Create images</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DropFile from "@/components/DropFile.vue";
import Popup from "@/components/PopupWindow.vue";
import WaitingBlock from "@/components/WaitingBlock.vue";

export default {
  name: "HomeView",
  components: {
    DropFile,
    Popup,
    WaitingBlock,
  },
  data() {
    return {
      headerText: "",
      subheaderText: "",
      showPopup: false,
      buttonDisabled: false,
      isInWaitMode: false,
      popupText: "",
    };
  },
  methods: {
    async onSubmit() {
      this.buttonDisabled = true;
      this.isInWaitMode = true;
      await this.$nextTick();

      const formData = new FormData();

      for (let i = 0; i < this.$refs.DropFile.files.length; i++) {
        formData.append("images", this.$refs.DropFile.files[i]);
      }

      formData.append("header", this.headerText);
      formData.append("subheader", this.subheaderText);
      console.debug(formData);
      const response = await fetch("api/uploadFiles", {
        method: "POST",
        body: formData,
      });
      const data = await response.json();

      this.isInWaitMode = false;
      if (data?.msg) {
        this.enablePopup(data.msg);
        return;
      }
      await this.$router.push(`/posts/${data.id}`);
    },
    async togglePopup() {
      this.popupText = "";
      this.buttonDisabled = false;
      this.showPopup = false;
    },
    enablePopup(msg) {
      this.popupText = msg;
      this.showPopup = true;
    },
  },
};
</script>

<style>

.input-block {
  display: flex;
  flex-direction: column;
  padding-left: 100px;
  justify-content: center;
  align-items: flex-start;
}

.input-row {
  font-size: 20px;
  width: 100%;

  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-bottom: 30px;
}

.input-row input {
  width: 100%;
}

.input-row label {
  
  text-align: left;
  padding-bottom: 5px; 
}

.button-outer {
  padding-top: 2.5%;
}



.input-field {
  font-size: 14px;
  flex: 1;
  padding: 8px;
  width: 100px;
  max-width: 50%; 
}

.button {
  width: 100%; 
  max-width: 150px; 
  height: 50px; 
  font-size: 18px;
  background-color: #EE6A1F;
  border: 2px solid #EE6A1F;
  color: white;
}

.button:hover {
  background-color: #ad511c; 
  border: 2px solid #ad511c;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.centered-text {
  padding: 7.5%;
  font-size: 32px;
}

.grid-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.drag-drop {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.column {
  width: 100%;
}

.drag-drop-text {
  margin-bottom: 10px;
}

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
