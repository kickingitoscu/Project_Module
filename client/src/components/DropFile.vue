<template>
  <div class="main">
    <div
      class="dropzone-container"
      :style="isDragging && 'border-color: green;'"
      @dragover="dragover"
      @dragleave="dragleave"
      @drop="drop"
    >
      <input
        type="file"
        multiple
        name="file"
        id="fileInput"
        class="hidden-input"
        @change="onChange"
        ref="file"
        accept=".pdf,.jpg,.jpeg,.png"
      />

      <label for="fileInput" class="file-label">
        <div v-if="isDragging">Release to drop files here.</div>
        <div v-else>Drop files here or <u>click here</u> to upload.</div>
      </label>
      <div class="preview-container mt-4" v-if="files.length">
        <div v-for="file in files" :key="file.name" class="preview-card">
          <img class="preview-img" :src="generateURL(file)" />
          <p>
            {{ file.name }}
          </p>
        </div>
        <div>
          <button
            class="ml-2"
            type="button"
            @click="remove(files.indexOf(file))"
            title="Remove file"
          >
            <b>×</b>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isDragging: false,
      files: [],
    };
  },
  methods: {
    onChange(e) {
      e.preventDefault();
      console.log(e.target.files);
      this.avoidDuplicates(e.target.files);
      this.flushRefList();
    },
    onDrop(files) {
      this.avoidDuplicates(files);
    },
    flushRefList() {
      this.$refs.file.files = new DataTransfer().files;
    },
    avoidDuplicates(fileList) {
      let incomingFiles = Array.from(fileList);
      console.log(incomingFiles);
      if (this.files.length > 0) {
        incomingFiles.forEach((incomingFile) => {
          if (
            this.files.find(
              (file) =>
                file.name === incomingFile.name &&
                file.size === incomingFile.size,
            )
          ) {
            // something happens
          } else {
            this.files.push(incomingFile);
          }
        });
      } else {
        console.debug("entered");
        this.files.push(...incomingFiles);
      }
    },

    dragover(e) {
      e.preventDefault();
      this.isDragging = true;
    },
    dragleave() {
      this.isDragging = false;
    },
    drop(e) {
      e.preventDefault();
      this.onDrop(e.dataTransfer.files);
      this.isDragging = false;
      console.log(this.files.length);
    },
    remove(i) {
      this.files.splice(i, 1);
    },
    generateURL(file) {
      let fileSrc = URL.createObjectURL(file);
      setTimeout(() => {
        URL.revokeObjectURL(fileSrc);
      }, 1000);
      return fileSrc;
    },
  },
};
</script>
<style scoped>
.main {
  display: flex;
  flex-grow: 1;
  align-items: center;
  height: 100vh;
  justify-content: center;
  text-align: center;
}

.dropzone-container {
  padding: 4rem;
  background: #f7fafc;
  border: 2px dashed;
  border-color: #9e9e9e;
}

.hidden-input {
  opacity: 0;
  overflow: hidden;
  position: absolute;
  width: 1px;
  height: 1px;
}

.file-label {
  font-size: 20px;
  display: block;
  cursor: pointer;
}

.preview-container {
  display: flex;
  margin-top: 2rem;
}

.preview-card {
  display: flex;
  border: 1px solid #a2a2a2;
  padding: 5px;
  margin-left: 5px;
}

.preview-img {
  width: 50px;
  height: 50px;
  border-radius: 5px;
  border: 1px solid #a2a2a2;
  background-color: #a2a2a2;
}
</style>
