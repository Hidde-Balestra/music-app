<template>
    <div>
        <div class="formContainer">
            <v-form @submit.prevent="login" v-model="form.valid" class="form">
                <h2>Login</h2>
                <v-text-field label="E-mail" v-model="form.values.email" :rules="form.rules.email"></v-text-field>
                <v-text-field label="Password" v-model="form.values.password" :rules="form.rules.password" :type="form.showPassword ? 'text' : 'password'" :append-inner-icon="form.showPassword ? 'mdi-eye-off' : 'mdi-eye'" @click:append-inner="form.showPassword = !form.showPassword"></v-text-field>
                <div class="buttonContainer">
                    <v-btn type="submit" color="gradient" block>Login</v-btn>
                    <span>
                        Do you not have an account yet? <a href="/register">Create one here</a>
                    </span>
                </div>
            </v-form>
        </div>

        <v-snackbar v-model="form.feedback.display" :timeout="3000" :color="form.feedback.success ? 'success' : 'error'">
            {{form.feedback.success ? 'Login successful!' : 'E-mail password combination incorrect.'}}
        </v-snackbar>
    </div>
</template>

<script>
import axios from "axios";


export default {
    data() {
        return {
            form: {
                valid: false,
                values: {
                    email: null,
                    password: null
                },
                rules: {
                    email: [val => (val?.length > 0) || 'E-mail is required.'],
                    password: [val => (val?.length > 0) || 'Password is required.'],
                },
                showPassword: false,
                feedback: {
                    display: false,
                    success: null
                }
            }
        };
    },
    methods: {
        login(){
            if (this.form.valid){
                
                const body = {
                    username: this.form.values.email,
                    password: this.form.values.password,
                }

                axios
                    .post(`${this.$config.apiUrl}/open/login`, body)
                    .then((response) => {
                        console.log({response});
                      localStorage.setItem('currentUserId', response.data.idUser)
                      localStorage.setItem('token', response.headers.authorization)
                        this.form.feedback.success = true;

                        // Redirect
                        setTimeout(() => {
                            location.replace('/');
                        }, 1500);
                    })
                    .catch((err) => {
                        console.error({err});
                        this.form.feedback.success = false;
                    })
                    .finally(() => {
                        this.form.feedback.display = true;
                    })

            }
        }
    }
}
</script>

<style lang="scss" scoped>

</style>