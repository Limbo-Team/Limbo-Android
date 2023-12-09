package com.igorj.auth_presentation.register


sealed class RegisterEvent {
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnNameChange(val name: String): RegisterEvent()
    data class OnSurnameChange(val surname: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    data class OnRepeatPasswordChange(val repeatPassword: String): RegisterEvent()
    object OnTogglePasswordVisibility: RegisterEvent()
    data class OnToggleIsStudent(val isChecked: Boolean): RegisterEvent()
    data class OnStudentIdChange(val studentId: String): RegisterEvent()
    object OnRegisterClick: RegisterEvent()
}