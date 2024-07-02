<template>
  <div class="player" :class="[{playerActive: currentAudio}, {isPlaying: isPlaying}]">
    <div v-if="isLoading" class="loader loader--style1" title="0">
      <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
           x="0px" y="0px"
           width="40px" height="40px" viewBox="0 0 40 40" enable-background="new 0 0 40 40" xml:space="preserve">
  <path opacity="0.2" fill="#000" d="M20.201,5.169c-8.254,0-14.946,6.692-14.946,14.946c0,8.255,6.692,14.946,14.946,14.946
    s14.946-6.691,14.946-14.946C35.146,11.861,28.455,5.169,20.201,5.169z M20.201,31.749c-6.425,0-11.634-5.208-11.634-11.634
    c0-6.425,5.209-11.634,11.634-11.634c6.425,0,11.633,5.209,11.633,11.634C31.834,26.541,26.626,31.749,20.201,31.749z"/>
        <path fill="#000" d="M26.013,10.047l1.654-2.866c-2.198-1.272-4.743-2.012-7.466-2.012h0v3.312h0
    C22.32,8.481,24.301,9.057,26.013,10.047z">
    <animateTransform attributeType="xml"
                      attributeName="transform"
                      type="rotate"
                      from="0 20 20"
                      to="360 20 20"
                      dur="0.5s"
                      repeatCount="indefinite"/>
    </path>
  </svg>
    </div>


    <div v-if="currentAudio" class="content">
      <h4>{{ currentAudio.sampleName }}</h4>
      <audio
          id="player-audio"
          :src="'data:audio/mpeg;base64,' + base64Encoded"
          :playingAudioId="currentAudio?.idMusicSample"
          controls
          controlsList="nodownload"
          @ended="onAudioEnded"
      >
        Your browser does not support the audio tag.
      </audio>

      <div class="audio-buttons">

        <button @click="document.getElementById('player-audio').currentTime -= 5" class="material-icons">replay_5
        </button>
        <button @click="document.getElementById('player-audio').currentTime -= 10" class="material-icons">replay_10
        </button>

        <div class="play-btn">
          <span class="material-icons gradient-text" v-if="!isPlaying" @click="playAudio">play_arrow</span>
          <span class="material-icons gradient-text" v-if="isPlaying" @click="pauseAudio">pause</span>
        </div>

        <button @click="document.getElementById('player-audio').currentTime += 10" class="material-icons">forward_10
        </button>
        <button @click="document.getElementById('player-audio').currentTime += 5" class="material-icons">forward_5
        </button>

      </div>


      <router-link class="nav-item" :to="{ name: 'profile', params: { id: currentAudio?.user?.idUser }}">
        <span class="material-icons">account_circle</span>
      </router-link>
    </div>
    <div v-else>No audio playing</div>

    <button class="player-close" @click="closePlayer"><span class="material-icons">close</span></button>
  </div>
</template>

<script>
import axios from "axios";
import config from "@/config";

export default {
  methods: {
    async getSampleBase64(sampleId) {
      try {
        let data = {
          sampleId: sampleId,
        }

        const response = await axios.post(
            `${config.apiUrl}/samples/retrieve`,
            data, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }
        );

        return response.data.base64Encoded ? response.data.base64Encoded : false;

      } catch (e) {
        console.log(e)
      }
    },
    playAudio() {
      let audioEl = document.getElementById('player-audio');

      this.isPlaying = true;

      audioEl?.play()
    },
    pauseAudio() {
      let audioEl = document.getElementById('player-audio');

      this.isPlaying = false;

      audioEl.pause()
    },
    onAudioEnded() {
      this.isPlaying = false;
    },
    async onAudioChange(newAudio) {
      this.isLoading = true;
      this.base64Encoded = await this.getSampleBase64(newAudio.idMusicSample);

      setTimeout(() => {
        this.isLoading = false;
        this.playAudio();
      }, 200);
    },
    closePlayer() {
      this.$store.commit('setCurrentAudio', null);
    }
  },
  watch: {
    '$store.state.currentAudio'(newValue, oldValue) {
      if (newValue) {
        this.onAudioChange(newValue);
      }
    },
  },
  computed: {
    currentAudio() {
      return this.$store.state.currentAudio;
    },
  },
  async created() {
  },
  data() {
    return {
      base64Encoded: "",
      isPlaying: false,
      document: document,
      isLoading: false,
    }
  },
};
</script>

<style lang="scss" scoped>
@import "../assets/styles/global.scss";


.loader {
  svg path,
  svg rect {
    fill: #FF6700;
  }
}

.player {
  z-index: 99;
  display: flex;
  gap: 20px;
  width: 100%;
  align-items: center;
  padding: 16px;
  float: left;
  position: fixed;
  bottom: -100%;
  left: 0;
  right: 0;
  background: $background-2;
  transition: .2s;

  .content {
    display: flex;
    gap: 20px;
    justify-content: space-between;
    width: 100%;
    align-items: center;

    .audio-buttons {
      display: flex;
      gap: 10px;
      align-items: center;
    }

    .nav-item {
      text-decoration: none;
    }
  }

  .player-close {
    margin-left: auto;
  }

  &.playerActive {
    bottom: 0;
  }

  audio {
    width: 100%;
    max-width: 600px;

    /* Webkit (Safari, Chrome, etc.) */
    &::-webkit-media-controls-panel {
      background: #e5e5e5;
    }

    &::-webkit-media-controls-play-button {
      display: none;
    }

    /* Firefox */
    &::-moz-media-controls-panel {
      background: #e5e5e5;
    }

    &::-moz-media-controls-play-button {
      display: none;
    }

    /* Microsoft Edge */
    &::-ms-media-controls-panel {
      background: #e5e5e5;
    }

    &::-ms-media-controls-play-button {
      display: none;
    }

    &.playing {
      background: pink;
    }
  }

  .play-btn {
    background: $background-3;
    height: 50px;
    width: 50px;
    border-radius: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: .2s;

    .material-icons {
      font-size: 30px;
    }

    &:hover {
      cursor: pointer;
      transform: scale(1.1);

      .material-icons {
        font-variation-settings: 'FILL' 1,
        'wght' 400,
        'GRAD' 0,
        'opsz' 24;
      }
    }
  }

  @media only screen and (max-width: 600px) {
    audio {
      display: none
    }
  }
}
</style>
