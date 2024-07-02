<template>
  <div class="music-sample" :class="[{ playing: isPlaying },{column: direction === 'column'}]"
       v-if="sample && !onlyPlayBtn">
    <div :class="{column: direction === 'column'}" class="content">
      <div class="btn-container">
        <div class="play-btn">
          <span class="material-icons gradient-text" @click=" onAudioStart(sample)">play_arrow</span>
          <!--          <span v-if="currentAudio?.idMusicSample === sample.idMusicSample" class="material-icons gradient-text" @click=" pauseAudio">pause</span>-->
        </div>
      </div>
      <div class="song-info">
        <h4>{{ sample?.sampleName }}</h4>
      </div>


      <button class="material-icons info-expand-btn" :class="{ expanded: expanded}" @click="expanded = !expanded;">
        expand_more
      </button>
    </div>

    <div class="expanded-info" v-if="expanded">
      <div class="content">
        <div class="sample-desc">
          <p v-if="sample?.sampleDescription">Description:<br>
            <strong>{{ sample.sampleDescription }}</strong>
          </p>
        </div>
        <div class="sample-stats">
          <p v-if="sample && sample.user && sample?.user?.idUser">Artist:<br>
            <router-link :to="{ name: 'profile', params: { id: sample?.user?.idUser }}">
              <strong>{{ sample.user?.firstName }} {{ sample.user?.lastName }}</strong>
            </router-link>
          </p>

          <p v-if="sample?.bpm">BPM:<br>
            <strong>{{ sample.bpm }}</strong>
          </p>

          <p v-if="sample?.plays">Plays:<br>
            <strong>{{ sample.plays }}</strong>
          </p>

          <p v-if="sample?.likes">Likes:<br>
            <strong>{{ sample.likes }}</strong>
          </p>

          <p v-if="sample?.dislikes">Dislikes:<br>
            <strong>{{ sample.dislikes }}</strong>
          </p>
        </div>
      </div>


      <div class="labels">
        <strong>Labels:</strong>
        <div class="label" v-for="label in sample?.labels">
          {{ label.name }}
        </div>
      </div>

      <div class="sample-interaction-btns">
        <button :class="{ materialFill: liked}" class="material-icons" @click="likeSample">thumb_up</button>
        <button :class="{ materialFill: disliked}" class="material-icons" @click="dislikeSample">thumb_down</button>
        <button class="material-icons" onclick="alert('Report sample')" v-if="false">flag</button>

      </div>
    </div>
  </div>

  <div class="play-btn" v-if="sample && onlyPlayBtn">
    <span class="material-icons gradient-text" @click=" onAudioStart(sample)">play_arrow</span>
  </div>
</template>

<script>
import axios from "axios";
import config from "@/config";

export default {
  props: {
    sample: {
      type: Object,
      required: true,
      user: {},
    },
    direction: {
      type: String,
      default: 'row'
    },
    onlyPlayBtn: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      genres: [],
      expanded: false,
      liked: false,
      disliked: false,
      isPlaying: false,
      document: document
    }
  },
  methods: {
    togglePlayPause(event) {
      let audioEl = document.getElementById(this.sample.idMusicSample + '-audio');
      audioEl.onended = function () {
        event.target.parentElement.querySelector('.material-icons').innerText = "play_arrow"
      };
      if (audioEl.paused) {
        onAudioStart(sample)
        audioEl.play();
        event.target.parentElement.querySelector('.material-icons').innerText = "pause"
      } else {
        audioEl.pause()
        event.target.parentElement.querySelector('.material-icons').innerText = "play_arrow"
      }
    },
    async likeSample() {
      this.liked = !this.liked

      try {
        let data = {
          sampleId: this.sample.idMusicSample,
          userId: this.currentUser?.user?.idUser
        }

        const response = await axios.post(
            `${config.apiUrl}/sample/like`,
            data, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }
        );

        return response.data ? response.data : false;

      } catch (e) {
        console.log(e)
      }

      if (this.liked) {
        this.disliked = false
      }
    },
    async dislikeSample() {
      this.disliked = !this.disliked

      try {
        let data = {
          sampleId: this.sample.idMusicSample,
          userId: this.currentUser?.user?.idUser
        }

        const response = await axios.post(
            `${config.apiUrl}/sample/dislike`,
            data, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }
        );

        return response.data ? response.data : false;

      } catch (e) {
        console.log(e)
      }

      if (this.disliked) {
        this.liked = false
      }
    },
    onAudioStart() {
      this.isPlaying = true;
      this.$store.commit('setCurrentAudio', this.sample);
    },
    onAudioEnded() {
      // ... (Your existing audio ended logic)
      this.isPlaying = false;
      // Clear the currentAudio in the Vuex store
      this.$store.commit('setCurrentAudio', null);
    },
    pauseAudio() {
      this.isPlaying = false;
      document.getElementById('player-audio').pause()
    }
  },
  computed: {
    currentAudio() {
      return this.$store.state.currentAudio;
    },
    currentUser() {
      return this.$store.state.currentUser;
    },
  },
  async created() {
    await this.$store.dispatch('fetchCurrentUser');

  },
}
</script>

<style lang="scss" scoped>
@import "../assets/styles/global.scss";


.music-sample {
  position: relative;
  background: #1A1A1A;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  padding: 20px;
  gap: 20px;
  overflow: visible;

  h4 {
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  &.column {
    width: 200px;
    justify-content: center;
    align-items: center;

    .expanded-info {
      width: fit-content;
      display: flex;
      flex-direction: column;
      gap: 20px;
      position: absolute;
      background: $background-3;
      border-radius: 10px;
      z-index: 9999;
      top: 100%;
      left: 0;
      padding: 20px;
    }
  }

  .content {
    display: flex;
    gap: 15px;


    &.column {
      flex-direction: column;
      justify-content: space-between;
      height: 100%;
      width: 100%;
      position: relative;

      .info-expand-btn {
        position: absolute;
        bottom: -15px;
        right: -15px;
        height: 20px;
        width: 20px;
        justify-content: center;
        align-items: center;
        display: flex;
      }
    }


    .btn-container {
      display: flex;
      justify-content: center;
      align-items: center;

    }

    .sample-desc {
      max-width: 200px;
    }

    .song-info {
      display: flex;
      //flex-direction: column;
      justify-content: center;
      align-items: center;

      h2 {
        overflow: hidden;
        text-overflow: ellipsis;
      }

      a {
        text-decoration: none;
      }
    }
  }

  .info-expand-btn {
    margin-left: auto;
    transition: .2s;

    &.expanded {
      transform: rotate(180deg);
    }
  }

  .expanded-info {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 20px;

    .content {
      display: flex;
      justify-content: space-between;
    }

    .labels {
      display: flex;
      gap: 5px;
      align-items: center;

      .label {
        padding: 4px 8px;
        background: $background-3;
        border-radius: 2345px;
      }
    }

    .sample-interaction-btns {
      display: flex;
      //display: none;
      gap: 10px;
    }
  }

  audio {
    filter: invert(1);

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


@media (max-width: 1000px) {

  .music-sample {
    .content {
      flex-direction: row;
    }
  }

}

</style>