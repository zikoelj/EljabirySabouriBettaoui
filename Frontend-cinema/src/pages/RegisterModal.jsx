import { Form, Formik } from 'formik';
import React from 'react'
import { useState } from 'react';
import { UserService } from '../services/userService'
import TextInputField from '../utils/customFormItems/TextInputField';
import { ToastContainer, toast } from 'react-toastify';

export default function RegisterModal() {

    const userService = new UserService();

    const registerCustomer = (values) => {

        if (values.password === values.passwordAgain) {

            let customer = {
                customerName: values.customerName,
                email: values.email,
                phone: values.phone,
                password: values.password
            };

            userService.addCustomer(customer).then(result => {
                if(result.status == 200) {
                    document.querySelector("#loginModalLink").click();
                    toast("Votre compte a été créé avec succès ! Veuillez vous connecter.", {
                        theme:"colored",
                        position:"top-center"
                    })
                }
            })
        } else {
            toast.error("Les valeurs de votre mot de passe ne correspondent pas.", {
                theme: "colored",
                position: "top-center"
            });
        }
    }

  return (
    <div>
        <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                <div class="modal-header login-modal-header">
                    <h5 class="modal-title" id="registerModalLabel">Devenir membre</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <Formik 
                    initialValues={{}}
                    onSubmit={(values) => {
                        registerCustomer(values);
                    }}>
                    <Form>
                        <div class="modal-body">
                            <div class="form-floating mb-3">
                                <TextInputField type="text" name="customerName" className="form-control" id="customerName" placeholder='Nom Prénom' required/>
                                <label for="customerName">Nom - Prénom</label>
                            </div>
                            <div className="form-floating mb-3">
                                <TextInputField type="email" name="email" className="form-control" id="email" placeholder='Email' required />
                                <label for="email">Email</label>
                            </div>
                            <div className="form-floating mb-3">
                                <TextInputField type="tel" name="phone" className="form-control" id="phone" placeholder='Téléphone'
                                                required />
                                <label for="phone">Téléphone - (+212) xxx xxx xxx</label>
                            </div>
                            <div className="form-floating mb-3">
                                <TextInputField type="password" name="password" className="form-control" id="password" placeholder='Mot de passe' required/>
                                <label for="password">Mot de passe</label>
                            </div>
                            <div className="form-floating mb-3">
                                <TextInputField type="password" name="passwordAgain" className="form-control" id="passwordAgain" placeholder='Nouveau mot de passe' required/>
                                <label for="passwordAgain">Nouveau mot de passe</label>
                            </div>
                            <p className='ps-2 text-start'>
                                Vous êtes déjà membre ?
                                <a href='!#' id="loginModalLink" style={{color:"black"}}
                                data-bs-toggle="modal" data-bs-target="#loginModal"> Se connecter </a>
                            </p>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary login-modal-btn">S'inscrire</button>
                        </div>
                    </Form>
                </Formik>
                </div>
            </div>
        </div>
        <ToastContainer />
    </div>
  )
}
