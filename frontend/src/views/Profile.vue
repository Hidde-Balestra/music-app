<template>
  <div>
    <div class="content" v-if="profile">
      <div class="left">
        <img
            class="profile-picture"
            :src="profilePicSrc"
            alt="profile picture"
        />

        <div v-if="profile && profile.description !== ''" class="profile-info">
          <div class="header-and-spacer">
            <p>Something about me</p>
            <div class="spacer"></div>
          </div>
          <p>{{ profile.description }}</p>
        </div>

        <div
            v-if="profile && profile.labels && profile.labels.filter(item => item.type === 'GENRE') && profile.labels.filter(item => item.type === 'GENRE').length > 0"
            class="profile-info"
        >
          <div class="header-and-spacer">
            <p>Genres</p>
            <div class="spacer"></div>
          </div>
          <div class="labels">
            <p v-for="genre in profile.labels.filter(item => item.type === 'GENRE' && item.description === null)"
               :key="genre.name">
              {{ genre.name }}</p>
          </div>
        </div>

        <div
            v-if="profile && profile.labels && profile.labels.filter(item => item.type === 'INSTRUMENT') && profile.labels.filter(item => item.type === 'INSTRUMENT').length > 0"
            class="profile-info">
          <div class="header-and-spacer">
            <p>Instruments</p>
            <div class="spacer"></div>
          </div>
          <div class="labels">
            <p v-for="instrument in profile.labels.filter(item => item.type === 'INSTRUMENT' && item.description === null)"
               :key="instrument.name">
              {{ instrument.name }}</p>
          </div>
        </div>
      </div>
      <div class="right">
        <div>
          <div class="top">
            <div>
              <h1>{{ profile.firstName }} {{ profile.lastName }}</h1>
              <h3 class="gradient-text">{{ profile.accountType }}</h3>
              <div class="statistics">
                <div class="stat">
                  <h4>Samples</h4>
                  <span v-if="profile && profile.samples">{{ profile.samples.length }}</span>
                </div>
                <div class="stat">
                  <h4>Likes</h4>
                  <span v-if="profile && profile.samples">{{
                      profile.samples.reduce((accumulator, sample) => {
                        return accumulator + sample.likes
                      }, 0)
                    }}</span>
                </div>
                <div class="stat" v-if="false">
                  <h4>Views</h4>
                  <span v-if="profile && profile.samples">{{
                      profile.samples.reduce((accumulator, sample) => {
                        return accumulator + sample.views
                      }, 0)
                    }}</span>
                </div>
              </div>
            </div>
            <div class="invites" v-if="isLoggedIn && this.$route.params.id === String(this.currentUser.user.idUser) && this.pendingInvites?.length > 0">
              <h4>Pending invites</h4>
              <div v-for="invite in this.pendingInvites" class="invite" :key="invite">
                <p>{{ invite.message }}</p>
                <div class="buttons">
                  <button @click="handleInvite('decline', invite.idSender)"><span class="material-icons">close</span></button>
                  <button @click="handleInvite('accept', invite.idSender)"><span class="material-icons">done</span></button>
                </div>
              </div>
            </div>
          </div>
          <div class="profile-buttons"
               v-if="isLoggedIn && this.$route.params.id !== String(this.currentUser.user.idUser)">
            <button v-if="!this.currentUserToThisProfileInvite"
                    class="primary-button" @click="sendInviteModal = !sendInviteModal">Send request
            </button>
            <div class="send-invite-modal-container" v-if="sendInviteModal">
              <div class="send-invite-modal">
                <h3>Send invite</h3>
                <span id="close-edit-profile-modal" class="material-icons" @click="sendInviteModal = false">close</span>
                <v-form @submit.prevent="sendCollabInvite">

                  <textarea v-model="sendInvite.message"
                            v-bind:placeholder="'Type your message to ' + profile.firstName + ' here..'"/>

                  <button type="submit" class="primary-button"><span class="material-icons">send</span>Send invite
                  </button>
                </v-form>
              </div>
            </div>

            <button class="secondary-button"  v-if="this?.currentUserToThisProfileInvite?.status === 'MATCH'"><span class="gradient-text">Contact</span></button>
            <a>Report user</a>
          </div>
          <div class="samples">
            <div class="header-and-spacer">
              <p>Samples</p>
              <div class="spacer"></div>
            </div>

            <v-btn @click="openUploadModal" v-if="isLoggedIn && this.$route.params.id === String(this.currentUser.user.idUser)" id="openUploadButton" class="mb-2">
              Upload
            </v-btn>

            <div v-if="profile.samples && profile.samples.length > 0" class="samples-list">

              <music-sample2
                  v-for="sample in profile.samples"
                  :sample="sample"
              ></music-sample2>


              </div>
            <p v-if="profile.samples && profile.samples.length === 0">
              This artist hasn't uploaded any samples yet.
            </p>
          </div>
        </div>
      </div>

      <button class="edit-profile-btn primary-button animate" :class="{playerActive: currentAudio}" @click="editProfile = !editProfile"
              v-if="isLoggedIn && this.$route.params.id === String(this.currentUser.user.idUser)">
        <span class="material-icons">edit</span>
      </button>
      <div class="edit-profile-modal-container" v-if="editProfile">
        <div class="edit-profile-modal">
          <h3>Edit profile</h3>
          <span id="close-edit-profile-modal" class="material-icons" @click="editProfile = false">close</span>
          <v-form @submit.prevent="updateUserProfile">

            <input v-model="updateParams.firstName" placeholder="First Name" type="text"/>
            <input v-model="updateParams.lastName" placeholder="Last Name" type="text"/>
            <v-autocomplete
                clearable
                chips
                multiple
                label="Genres"
                v-model="updateParams.genres"
                :items="options.genres"
                item-title="name"
                item-value="id"
            ></v-autocomplete>
            <v-autocomplete
                clearable
                chips
                multiple
                label="Instruments"
                v-model="updateParams.instruments"
                :items="options.instruments"
                item-title="name"
                item-value="id"
            ></v-autocomplete>

            <button type="submit" class="primary-button"><span class="material-icons">save</span>Save changes</button>
          </v-form>
        </div>
      </div>
    </div>
    <div class="content" v-if="!profile">
      <div class="profile-not-found">
        <h2>Profile not found</h2>
        <p>The profile you are looking for doesn't exist (anymore).</p>
        <router-link to="/">
          <button class="secondary-button"><span class="gradient-text">Take me back home</span></button>
        </router-link>
      </div>
    </div>

    <v-dialog v-model="modal.upload.display" width="700" id="uploadDialog">
      <v-card>
        <v-card-title>
          Upload music samples
        </v-card-title>

        <v-card-text>
          Upload your music samples here. You can upload multiple samples at once.
        </v-card-text>

        <VueFileAgent
            v-model="modal.upload.selectedFiles"
            :deletable="true"
            :multiple="true"
            accept="audio/*"
            @select="onFileSelect($event)"
            @beforedelete="onFileDelete($event)"
        ></VueFileAgent>

        <table v-if="modal.upload.files.length" class="uploadTable mt-3">
          <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>BPM</th>
            <th>Genres</th>
            <th>Instruments</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(file, index) in modal.upload.files" :key="index">
            <td>
              <v-text-field
                  v-model="file.name"
              ></v-text-field>
            </td>
            <td>
              <v-text-field
                  v-model="file.description"
              ></v-text-field>
            </td>
            <td>
              <v-text-field
                  v-model="file.bpm"
              ></v-text-field>
            </td>
            <td>
              <v-autocomplete
                  clearable
                  chips
                  multiple
                  v-model="file.genres"
                  :items="options.genres"
                  item-title="name"
                  item-value="id"
              ></v-autocomplete>
            </td>
            <td>
              <v-autocomplete
                  clearable
                  chips
                  multiple
                  v-model="file.instruments"
                  :items="options.instruments"
                  item-title="name"
                  item-value="id"
              ></v-autocomplete>
            </td>
          </tr>
          </tbody>
        </table>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="modal.upload.display = false">
            Cancel
          </v-btn>
          <v-btn @click="uploadFiles" class="bg-gradient" color="gradient" :disabled="modal.upload.saveButtonDisabled" id="uploadButton">
            Upload
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar v-model="modal.upload.feedback.display" :timeout="1500"
                :color="modal.upload.feedback.success ? 'success' : 'error'">
      {{ modal.upload.feedback.success ? 'Upload successful!' : 'Something went wrong.' }}
    </v-snackbar>
  </div>

</template>
<script>
import axios from "axios";
import {mapGetters, mapState} from "vuex";
import config from "@/config";
import * as meta from 'music-metadata-browser';

import MusicSample2 from "@/components/MusicSample2";

async function getUserProfile(idUser) {

  try {
    const response = await axios.get(`${config.apiUrl}/profile?idUser=${idUser}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching user profile:', error);
    return {};
  }
}

export default {

  data() {
    return {
      profile: {},
      profilePicSrc: "https://t3.ftcdn.net/jpg/05/16/27/58/360_F_516275801_f3Fsp17x6HQK0xQgDQEELoTuERO4SsWV.jpg",
      editProfile: false,
      pendingInvites: [],
      currentUserToThisProfileInvite: {},
      sendInviteModal: false,
      sendInvite: {
        message: "",
        idSender: localStorage.getItem('currentUserId'),
        idRecipient: null,
      },
      updateParams: {
        firstName: '',
        lastName: '',
        genres: [],
        instruments: [],
        labels: [],
        // Add more fields as needed
      },
      options: {
        genres: [],
        instruments: [],
      },
      currentUserId: null,

      modal: {
        upload: {
          saveButtonDisabled: false,
          display: false,
          selectedFiles: [],
          files: [],
          feedback: {
            display: false,
            success: true,
          }
        }
      }

    };
  },
  name: "Profile",
  computed: {
    ...mapGetters(['isLoggedIn']),
    ...mapState({
      currentUser: 'currentUser',
    }),
    currentAudio() {
      return this.$store.state.currentAudio;
    },
  },
  components: {
    MusicSample2
  },
  methods: {
    togglePlayPause(event) {
      let audioEl = event.target.parentElement.parentElement.querySelector('audio');
      audioEl.onended = function () {
        event.target.parentElement.querySelector('.material-icons').innerText = "play_arrow"
      };
      if (audioEl.paused) {
        audioEl.play();
        event.target.parentElement.querySelector('.material-icons').innerText = "pause"
      } else {
        audioEl.pause()
        event.target.parentElement.querySelector('.material-icons').innerText = "play_arrow"
      }
    },

    createFileFormData(file) {

      const dateNow = new Date().toISOString();

      const musicSample = {
        idUser: this.profile.idUser,
        sampleName: file.name,
        sampleExtension: file.extension,
        sampleLength: file.length,
        bpm: file.bpm,
        sampleDescription: file.description,
        dateCreated: dateNow,
        dateModified: dateNow,
      };

      // Create form data
      let formData = new FormData();
      formData.append('musicSample', new Blob([JSON.stringify(musicSample)], {
        type: 'application/json'
      }));
      formData.append('file', file.file);

      return formData;

    },

    uploadFiles() {
      console.log('upload files');

      // No Files selected
      if (this.modal.upload.files.length === 0) {
        this.modal.upload.feedback.success = false;
        this.modal.upload.feedback.display = true;
        return;
      }

      // Upload files
      Promise.all(
          this.modal.upload.files.map((file) => this.uploadFileLinkLabels(file))
      )
          .then(() => {
            // File upload successful
            console.log('all files uploaded');
            this.modal.upload.feedback.success = true;
            this.modal.upload.saveButtonDisabled = false;
            this.modal.upload.display = false;
          })
          .catch((err) => {
            // File upload failed
            this.modal.upload.feedback.success = false;
            this.modal.upload.saveButtonDisabled = false;
            console.error('error uploading files', err);
          })
          .finally(() => {
            // Show feedback
            this.modal.upload.feedback.display = true;
          })

    },

    uploadFileLinkLabels(file) {
      console.log('upload file, link labels', JSON.parse(JSON.stringify(file)));

      return new Promise((success, failure) => {
        new Promise((resolve) => {

          // Upload sample
          const fileFormData = this.createFileFormData(file);
          axios
              .post(`${this.$config.apiUrl}/samples/upload`, fileFormData, {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              })
              .then((response) => {
                const musicSampleId = (response.data != -1) ? response.data : null;
                musicSampleId ? resolve(musicSampleId) : failure();
              })
              .catch((err) => {
                console.error({err});
                failure();
              })

        })
        .then((musicSampleId) => {

          // Link labels to sample
          const labelIds = file.genres.concat(file.instruments);

          const body = {
            musicSampleId,
            labelIds,
          };

          axios
              .post(`${this.$config.apiUrl}/link-labels-to-sample`, body)
              .then((response) => {
                response.data ? success() : failure();
              })
              .catch((err) => {
                console.error({err});
                failure();
              })

        })
        .catch((err) => {
          console.error({err});
          failure();
        })
      });
    },

    splitNameAndExtension(fileNameAndExtension) {
      const nameArr = fileNameAndExtension.split('.');
      const extension = nameArr.pop();
      const name = nameArr.join('.');
      return {name, extension};
    },

    openUploadModal() {
      this.modal.upload.saveButtonDisabled = false;
      this.modal.upload.display = true;
    },

    // Vue file agent

    async onFileSelect(files) {
      console.log('file selected', JSON.parse(JSON.stringify(files)));

      const newFilesPromises = files.map(async (file) => {
        const metadata = await meta.parseBlob(file.file);
        const bpm = metadata.common.bpm || null;
        const length = Math.ceil(metadata.format.duration) || null;
        const {name, extension} = this.splitNameAndExtension(file.raw.name);

        return {
          id: file.id,
          file: file.file,
          name,
          extension,
          bpm,
          length,
          description: '',
          genres: [],
          instruments: [],
        }
      });

      Promise.all(newFilesPromises)
      .then((newFiles) => {
        this.modal.upload.files.push(...newFiles);
      })
      .catch((err) => console.error({err}));
    },

    onFileDelete(file) {
      console.log('file deleted', JSON.parse(JSON.stringify(file)));  

      this.modal.upload.files = this.modal.upload.files.filter((item) => item.id !== file.id);
    },

    async updateUserProfile() {
      try {

        if (!this.updateParams.genres) {
          this.updateParams.labels = JSON.stringify([...this.updateParams.instruments])
        } else {
          this.updateParams.labels = JSON.stringify([...this.updateParams.genres, ...this.updateParams.instruments])
        }


        const updateParamsCopy = {}

        for (const key in this.updateParams) {
          if (key === 'genres' || key === 'instruments') {
            continue
          }
          const value = this.updateParams[key];

          if (value !== '' && value !== null && value !== undefined && !(Array.isArray(value) && value.length === 0)) {
            updateParamsCopy[key] = value;
          }
        }


        let reqConfig = {
          method: 'put',
          maxBodyLength: Infinity,
          url: `${config.apiUrl}/profile/update/${this.currentUser.user.idUser}`,
          headers: {
            'Content-Type': 'application/json'
          },
          data: JSON.stringify(updateParamsCopy)
        };

        axios.request(reqConfig)
            .then((response) => {
              if (response.status === 200) {
                location.reload()
              }
            }).catch((error) => {
          console.log(error);
        });

      } catch (error) {
        console.error('Error updating profile:', error);
        // Handle errors, show an error message, etc.
      }
    },
    getLabelsOfType(type) {
      return new Promise((resolve, reject) => {

        axios
            .get(`${this.$config.apiUrl}/labels/type/${type}`)
            .then((response) => {
              resolve(
                  response.data.sort((a, b) => a.name.localeCompare(b.name))
              )
            })
            .catch((err) => {
              console.error({err});
              reject()
            })

      });
    },
    async sendCollabInvite() {
      try {


        const response = await axios.post(
            `${config.apiUrl}/invite/create`,
            this.sendInvite,
        );

        if (response.status === 200) {
          this.sendInvite.message = "";
          this.sendInviteModal = false;
        }

      } catch (error) {
        console.error('Error sending invite:', error);
        let errorMessage = document.createElement('p')
        errorMessage.textContent = error.response.data
        errorMessage.classList = "error-message";

        document.querySelector('.send-invite-modal').appendChild(errorMessage);
      }
    },
    async getPendingInvitesForRecipientId(recipientId) {
      try {
        const response = await axios.get(
            `${config.apiUrl}/invites/pending/${recipientId}`
        );

        return response.data.filter(invite => invite.status === "PENDING");
      } catch (e) {
        console.log(e)
      }
    },
    async currentUserSentInviteToThisProfile() {
      try {
        let data = {
          idSender: localStorage.getItem('currentUserId'),
          idRecipient: String(this.profile.idUser)
        }

        const response = await axios.post(
            `${config.apiUrl}/invite/retrieve`,
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
    },
    async handleInvite(action, idSender) {

      let options = [
          'accept',
          'decline'
      ]

      if (options.indexOf(action) === -1){
        return false;
      }

      try {
        let data = {
          idSender: String(idSender),
          idAccepter: String(this.currentUser.user.idUser)
        }

        const response = await axios.post(
            `${config.apiUrl}/invite/${action}`,
            data, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }
        );

        if (response.data === true){
          this.pendingInvites = await this.getPendingInvitesForRecipientId(this.currentUser.user.idUser)
        }

        return response.data
      } catch (e) {
        console.log(e)
      }
    }

  }
  ,
  async created() {
    // Fetch the current user profile when the component is created
    if (this.$route.params.id === localStorage.getItem('currentUserId')) {
      await this.$store.dispatch('fetchCurrentUser');

      this.profile = {
        ...this.currentUser?.user,
        samples: this.currentUser?.samples,
      };

      this.pendingInvites = await this.getPendingInvitesForRecipientId(this.profile.idUser)


    } else {
      const userProfile = await getUserProfile(this.$route.params.id);
      this.profile = {
        ...userProfile?.user,
        samples: userProfile?.samples || [],
      };

      this.sendInvite.idRecipient = this.profile.idUser
      this.currentUserToThisProfileInvite = await this.currentUserSentInviteToThisProfile()

    }

    this.options.genres = await this.getLabelsOfType('genre');
    this.options.instruments = await this.getLabelsOfType('instrument');
    this.updateParams.genres = this.profile.genres;
    this.currentUserId = localStorage.getItem('currentUserId');

    console.log(JSON.parse(JSON.stringify(this.profile)))

  },
};

</script>


<style lang="scss" scoped>
@import "../assets/styles/global.scss";


.content {
  display: flex;
  max-width: 1400px;
  gap: 40px;
  margin-inline: auto;

  .profile-info {
    padding-block: 20px;

    .labels {
      display: flex;
      gap: 5px;
      flex-wrap: wrap;

      p {
        background: $background-2;
        padding: 5px 12px;
        border-radius: 1000px;
      }
    }
  }

  .header-and-spacer {
    display: flex;
    white-space: nowrap;
    align-items: center;
    gap: 5px;
    height: min-content;
    margin-bottom: 10px;

    .spacer {
      width: 100%;
      height: 1px;
      background: $text-primary;
      min-width: 20px;
    }
  }

  .left {
    width: 35%;
    min-width: 35%;
    overflow-x: hidden;

    .profile-picture {
      width: 100%;
      border-radius: 24px;
    }
  }

  .right {
    width: 100%;

    .top {
      display: flex;
      width: 100%;
      justify-content: space-between;
      gap: 20px;

      .invites {
        display: flex;
        flex-direction: column;
        width: 50%;
        gap: 5px;

        .invite {
          background: $background-2;
          padding: 5px;
          border-radius: 4px;
          overflow: hidden;
          display: flex;
          align-items: center;
          justify-content: space-between;

          .buttons {
            display: flex;
            gap: 5px;

            button {
              background: $background-3;
              height: 25px;
              width: 25px;
              display: flex;
              justify-content: center;
              align-items: center;
              padding: 3px;
              border-radius: 4px;
              transition: .2s;

              &:hover {
                transform: scale(1.1);
              }
            }
          }

          p {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
          }
        ;
        }
      }
    }

    .statistics {
      display: flex;
      gap: 20px;
      padding-block: 20px;

      .stat {
        h4 {
          font-weight: 300;
        }

        span {
          font-weight: bold;
          font-size: 1.5em;
        }
      }
    }

    .profile-buttons {
      align-items: center;
      display: inline-flex;
      gap: 35px;
      //padding: 10px;
      position: relative;

    }

    .samples {
      display: flex;
      flex-direction: column;
      gap: 5px;
      padding-block: 20px;

      .samples-list{
        display: flex;
        flex-direction: column;
        gap: 5px;

        .sample {
          display: flex;
          width: 100%;
          background: $background-2;
          border-radius: 12px;
          align-items: center;
          padding: 20px;
          gap: 20px;

          audio {
            filter: invert(1);

            &::-webkit-media-controls-panel {
              background: #e5e5e5;
            }

            &::-webkit-media-controls-play-button {
              display: none;
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
        }
      }
    }
  }

  .edit-profile-btn {
    position: fixed;
    right: 20px;
    bottom: 20px;
    transition: .2s;

    &.playerActive{
      bottom: 106px;
    }
  }
  .send-invite-modal-container{
    z-index: 10;
  }

  .edit-profile-modal-container, .send-invite-modal-container {
    width: 100%;
    height: 100%;
    position: fixed;
    background: rgb(17, 17, 17, .8);
    display: flex;
    justify-content: center;
    align-items: center;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;

    .edit-profile-modal, .send-invite-modal {
      margin: auto;
      background: $background-2;
      padding: 20px;
      gap: 20px;
      display: flex;
      flex-direction: column;
      border-radius: 16px;
      position: relative;
      max-width: 60vw;


      .error-message {
        color: darkred;
      }

      #close-edit-profile-modal {
        position: absolute;
        top: 10px;
        right: 10px;
        padding: 0;
        height: 20px;
        width: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
      }

      form {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 10px;

        textarea {
          width: 100%;
          min-width: 250px;
          min-height: 150px;
          color: white;
        }

        .v-input {
          width: 100%;
        }

        input {
          background: $background-3;
          padding: 10px 20px;
          border-radius: 8px;
          color: $text-primary;
        }
      }

      button {
        margin-top: 20px;
        width: 100%;

        .material-icons {
          margin-right: 10px;
        }
      }
    }
  }

  .profile-not-found {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 20px;
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;

    a {
      text-decoration: none;
    }
  }
}

.uploadTable {
  width: 100%;

  th {
    text-align: left;
  }
}

@media only screen and (max-width: 920px) {
  .content {
    flex-direction: column;
    padding-bottom: 150px;

    .left {
      order: 2;
      width: 100%;

      .profile-picture {
        max-width: 250px;
      }
    }

    .right {
      order: 0;
    }
  }
}
</style>