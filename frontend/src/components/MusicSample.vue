<template>
  <div class="music-sample" :class="highlight ? 'highlight' : ''" v-if="sample">

    <div class="content">
      <div class="btn-container">
        <div class="play-btn">
          <span class="material-icons gradient-text">play_arrow</span>
        </div>
      </div>
      <div class="song-info">
        <h2>{{ sample?.sampleName }}</h2>
        <p v-if="sample?.user?.idUser">Artist:
          <router-link :to="{ name: 'profile', params: { id: sample?.user?.idUser }}">{{ sample.user?.firstName }}
            {{ sample.user?.lastName }}
          </router-link>
        </p>
      </div>
    </div>

    <div class="song-genres" v-if="highlight && genres.length">
      <div class="genre" v-for="genre in genres" :key="genre.id">
        <i class="mdi mdi-speedometer"></i> {{ genre.name }}
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import config from "@/config";

export default {
  props: {
    sample: {
      type: Object,
      required: true
    },
    highlight: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      genres: []
    }
  },
  methods: {
    async getArtistOfThisSample() {
      try {
        const response = await axios.get(`${config.apiUrl}/profile?idUser=${this.sample.idUser}`)

        return response.data.user
      } catch (e) {
        console.log(e)
      }
    },
  },
  async created() {
    if (this.highlight && this?.sample?.labels?.length) {
      this.genres = this.sample.labels.slice(0, 5);
    }

    // this.sample.user = await this.getArtistOfThisSample();
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
  overflow: hidden;

  .content {
    display: flex;
    flex-direction: column;
    gap: 15px;

    .btn-container {
      display: flex;
      justify-content: center;
      align-items: center;

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
    }

    .song-info {
      display: flex;
      flex-direction: column;
      justify-content: center;

      h2 {
        overflow: hidden;
        text-overflow: ellipsis;
      }

      a {
        text-decoration: none;
      }
    }
  }

  &.highlight {
    flex-direction: row;
    padding: 40px 20px;
    justify-content: space-between;

    .content {
      flex-direction: row;
      gap: 20px;

      .btn-container {
        flex-direction: column;

        .play-btn {
          height: 80px;
          width: 80px;

          .material-icons {
            font-size: 50px;
          }
        }
      }

      .song-info {

        h2 {
          font-size: 2.5em;
        }

        p {
          font-size: 1.2em;
        }
      }
    }

    .song-genres {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;
      max-width: 500px;

      .genre {
        font-size: 1em;
        font-weight: bold;
        background: $background-3;
        padding: 15px 20px;
        border-radius: 100px;
        display: flex;
        gap: 10px;
        align-self: flex-start;
      }
    }
  }
}


@media (max-width: 1000px) {

  .music-sample {
    .content {
      flex-direction: row;
    }

    &.highlight {
      .song-genres {
        display: none;
      }
    }
  }

}

</style>