<template>
  <div v-if="images.length > 0">
    <div class="image-wrapper">
      <div v-for="(image, index) in images" :key="index" class="image-item">
        <img :src="image.url" alt="Image" class="image" />
        <div class="beauty-rank">Beauty Rank: {{ image.rank }}</div>
      </div>
    </div>
    <button @click="downloadImages" class="download-button">
      Download All
    </button>
  </div>
</template>

<script>
import JSZip from "jszip";

export default {
  name: "PostsView",
  data() {
    return {
      images: [],
    };
  },
  async mounted() {
    // Access the post ID
    const postId = this.$route.params.param;
    const res = await fetch(`/getPosts?id=${postId}`);
    const data = await res.json();
    this.images = data.images.map((imageObject, index) => {
      console.debug(index);
      const byteCharacters = atob(imageObject.data);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      const blob = new Blob([byteArray], { type: "image/jpeg" });

      // Create URL for Blob object
      const url = URL.createObjectURL(blob);

      return { url: url, rank: imageObject.rank };
    });
    this.images.sort((a, b) => b.rank - a.rank);
  },
  methods: {
    async downloadImages() {
      const zip = new JSZip();
      const imgFolder = zip.folder("images");
      for (let i = 0; i < this.images.length; i++) {
        const image = this.images[i];
        const response = await fetch(image.url);
        const blob = await response.blob();
        imgFolder.file(`image_rank_${image.rank}.jpg`, blob);
      }

      const zipBlob = await zip.generateAsync({ type: "blob" });
      const link = document.createElement("a");
      link.href = URL.createObjectURL(zipBlob);
      link.download = "images.zip";
      link.click();
    },
  },
};
</script>

<style>
.image-container {
  width: 100%;
  overflow-x: auto;
  white-space: nowrap;
}

.image-wrapper {
  display: inline-block;
}

.image-item {
  display: inline-block;
  margin-right: 10px;
}

.image {
  width: 200px;
  height: auto;
}

.beauty-rank {
  font-size: 14px;
  color: #888;
}
</style>
