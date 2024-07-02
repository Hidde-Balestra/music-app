<template>
    <div>
        <div class="formContainer">
            <v-form @submit.prevent="register" v-model="form.valid" class="form">
                <h2>Register</h2>
                <div v-if="form.page == 1" class="formPage">
                    <v-text-field label="E-mail" v-model="form.values.email" :rules="form.rules.email"></v-text-field>
                    <v-text-field label="Password" v-model="form.values.password" :rules="form.rules.password" :type="form.showPassword ? 'text' : 'password'" :append-inner-icon="form.showPassword ? 'mdi-eye-off' : 'mdi-eye'" @click:append-inner="form.showPassword = !form.showPassword" ref="passwordConfirm"></v-text-field>
                    <v-text-field label="Confirm password" v-model="form.values.passwordConfirm" :rules="form.rules.passwordConfirm" :type="form.showPasswordConfirm ? 'text' : 'password'" :append-inner-icon="form.showPasswordConfirm ? 'mdi-eye-off' : 'mdi-eye'" @click:append-inner="form.showPasswordConfirm = !form.showPasswordConfirm"></v-text-field>
                    <div class="buttonContainer">
                        <v-btn color="gradient" @click="nextPage" block>Next</v-btn>
                        <span>
                            Already have an account? <a href="/login">Login here</a>
                        </span>
                    </div>
                </div>
                <div v-if="form.page == 2" class="formPage">
                    <v-text-field label="First name" v-model="form.values.firstName" :rules="form.rules.firstName"></v-text-field>
                    <v-text-field label="Last name" v-model="form.values.lastName" :rules="form.rules.lastName"></v-text-field>
                    <v-text-field label="Phone number" v-model="form.values.phoneNumber" :rules="form.rules.phoneNumber"></v-text-field>
                    <v-select label="Account type" v-model="form.values.accountType" :rules="form.rules.accountType" :items="form.options.accountType" item-title="label" item-value="value"></v-select>
                    
                    <div class="buttonContainer">                    
                        <v-btn color="gradient" @click="nextPage" block>Next</v-btn>
                        <v-btn color="dark" block @click="form.page--">Back</v-btn>
                    </div>
                </div>
                <div v-if="form.page == 3" class="formPage">
                    <v-autocomplete
                        clearable
                        chips
                        label="Genres"
                        v-model="form.values.genres"
                        :items="form.options.genres"
                        item-title="name"
                        item-value="id"
                        multiple
                    ></v-autocomplete>

                    <v-autocomplete
                        clearable
                        chips
                        label="Instruments"
                        v-model="form.values.instruments"
                        :items="form.options.instruments"
                        item-title="name"
                        item-value="id"
                        multiple
                    ></v-autocomplete>

                    <v-checkbox v-model="form.values.terms" :rules="form.rules.terms">
                        <template v-slot:label>
                            <div>
                                I agree to the
                                <v-tooltip location="bottom">
                                    <template v-slot:activator="{ props }">
                                    <router-link
                                        target="_blank"
                                        :to="{ name: 'terms' }"
                                        v-bind="props"
                                    >
                                        Terms and conditions
                                    </router-link>
                                    </template>
                                    Opens in new window
                                </v-tooltip>
                            </div>
                        </template>
                    </v-checkbox>

                    <div class="buttonContainer">
                        <v-btn type="submit" color="gradient" block>Register</v-btn>
                        <v-btn color="dark" block @click="form.page--">Back</v-btn>
                    </div>
                </div>
            </v-form>
        </div>

        <v-snackbar v-model="form.feedback.display" :timeout="3000" :color="form.feedback.success ? 'success' : 'error'">
            {{form.feedback.success ? 'Registration successful!' : 'Something went wrong.'}}
        </v-snackbar>
    </div>
</template>

<script>
import axios from "axios";

export default {
    async created() {
        this.form.options.genres = await this.getLabelsOfType('genre');
        this.form.options.instruments = await this.getLabelsOfType('instrument');
    },
    data() {
        return {
            form: {
                valid: false,
                values: {
                    email: null,
                    password: null,
                    passwordConfirm: null,
                    firstName: null,
                    lastName: null,
                    phoneNumber: null,
                    accountType: null,
                    genres: [],
                    instruments: [],
                    terms: false,
                },
                options: {
                    accountType: [
                        {label: 'Artist', value: 'ARTIST'},
                        {label: 'Manager', value: 'MANAGER'},
                    ],
                    genres: [],
                    instruments: [],
                },
                rules: {
                    firstName: [val => (val?.length > 0) || 'First name is required.'],
                    lastName: [val => (val?.length > 0) || 'Last name is required.'],
                    email: [
                        val => (/.+@.+\..+/.test(val)) || 'E-mail must be valid.',
                        async val => await this.checkEmailAvailable(val) || 'E-mail is already taken.'
                    ],
                    password: [
                        val => (val?.length >= 8) || 'Password must be atleast 8 characters.',
                        val => (/\d/.test(val)) || 'Password must contain a digit.',
                        val => (/[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/.test(val)) || 'Password must contain a special character.',
                    ],
                    passwordConfirm: [val => val == this.form.values.password || 'Password must be equal.'],
                    phoneNumber: [val => (/^\d+$/.test(val)) || 'Phone number must be valid. Please only use numbers.'],
                    accountType: [val => (val?.length > 0) || 'Account type is required.'],
                    terms: [val => (val) || 'Agreeing to the terms and conditions is required.'],
                },
                showPassword: false,
                showPasswordConfirm: false,
                page: 1,
                feedback: {
                    display: false,
                    success: null
                }
            }
        };
    },
    methods: {
        checkEmailAvailable(email){
            return new Promise((resolve, reject) => {
                axios
                    .get(`${this.$config.apiUrl}/open/is_available`, {params: {emailAddress: email}})
                    .then((response) => {
                        console.log({response});
                        resolve(response.data)
                    })
                    .catch((err) => {
                        console.error({err});
                        reject();
                    })
            });
        },
        register(){
            if (this.form.valid){
                    
                const dateNow = new Date().toISOString();

                const body = {
                    ...this.form.values,
                    description: '',
                    dateCreated: dateNow,
                    dateModified: dateNow,
                }

                // Register user
                axios
                    .post(`${this.$config.apiUrl}/open/register_account`, body)
                    .then((response) => {
                        console.log({ response });
                        return response.data;
                    })
                    .then((userId) => {
                        const labelIds = this.form.values.instruments.concat(this.form.values.genres);

                        const body = {
                            userId,
                            labelIds,
                        };

                        // Add genre and instrument labels to user
                        return axios.post(`${this.$config.apiUrl}/link-labels-to-user`, body);
                    })
                    .then((response) => {
                        this.form.feedback.success = true;
                    })
                    .catch((err) => {
                        console.error({ err });
                        this.form.feedback.success = false;
                    })
                    .finally(() => {
                        this.form.feedback.display = true;

                        if (this.form.feedback.success){
                            // Redirect
                            setTimeout(() => {
                                location.replace('/login');
                            }, 1500);
                        }
                    });

            }
        },
        getLabelsOfType(type){
            return new Promise((resolve, reject) => {
                
                axios
                    .get(`${this.$config.apiUrl}/labels/type/${type}`)
                    .then((response) => {
                        console.log({response});
                        resolve(
                            response.data.sort((a,b) => a.name.localeCompare(b.name))
                        )
                    })
                    .catch((err) => {
                        console.error({err});
                        reject()
                    })

            });
        },
        nextPage(){
            if (this.form.valid){
                this.form.page++;
            }
        },
    }
}
</script>

<style lang="scss" scoped>

</style>