

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
    // const postId = this.$route.params.param;
    const res = await fetch(`/getPosts?id=xd6M5ZpT0IYMvGNJ`);
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
    getHomePage() {
      this.$router.push(`/`);
    }
  },
};
</script>

<template>
  <div class="container">
    <div class="centered-text">
      These are the generated posts with highest beauty rank!
    </div>
    <div class="image-container">
      <div class="image-wrapper">
        <div class="image-items-container">
          <div v-for="(image, index) in images" :key="index" class="image-item">
            <img :src="image.url" alt="Image" class="image-with-border" />
            <div class="beauty-rank">Beauty Rank: {{ image.rank }}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-wrapper">
      <div class="button-container">
        <button @click="downloadImages" class="download-button">
          Download All
        </button>
      </div>
      <div class="button-container">
        <button @click="getHomePage" class="home-button">
          Back to Homepage
        </button>
      </div>
    </div>
  </div>
</template>

<style>

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.centered-text {
  padding: 4.5%; 
  font-size: 32px;
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh;
  overflow: hidden;
  width: 100%; 
  margin: 0px;
}

.image-items-container {
  display: flex;
  padding-top: 10px;
}

.image-item:last-child {
  padding-right: 20px;
}

.image-wrapper {
  display: flex;
  flex-wrap: nowrap;
  border: 1px solid #ccc;
  padding: 10px;
  overflow-x: auto;
  width: 80%; 
  margin: 0 auto; 
}

.image-item {
  flex: 0 0 auto; 
  padding: 10px;
  text-align: center;
}

.image-with-border {
  max-width: 300px;
  height: auto;
  border: 1px solid #000;
}

.beauty-rank {
  font-size: 20px;
  color: #666666;
  margin-top: 10px;
}

.button-wrapper {
  display: flex;
  justify-content: center;
  padding-top: 15px;
}

.button-container {
  padding: 0 10px; 
}

.download-button {
  width: 100%; 
  max-width: 150px; 
  height: 50px; 
  font-size: 18px;
  background-color: #EE6A1F;
  border: 2px solid #EE6A1F; 
  color: white;
  cursor: pointer;
  text-align: center;
  transition: background-color 0.3s ease;
}

.home-button {
  width: 100%; 
  max-width: 150px; 
  height: 50px; 
  font-size: 18px;
  color: white;
  cursor: pointer;
  text-align: center;
  transition: background-color 0.3s ease;
  background-color: #666;
  border: 2px solid #666;
}

.home-button:hover {
  background-color: #363434; 
  border: 2px solid #363434;
}

.download-button:hover {
  background-color: #ad511c;
  border: 2px solid #ad511c;
}

* {
  font-family: 'Open Sans', Arial, sans-serif;
}

</style>