let userData = {
    name: "Sample Data",
    userId: "Sample-Id",
    email: "sample@gmail.com",
    phone: "8675634820",
    gender: "Male",
    role: "Judge",
    photoUrl: "../../images/user.png",
    password: "password123" // This is just for demonstration. In a real app, never store passwords in plain text.
};

function updateProfile(data) {
    document.getElementById("profileName").textContent = data.name;
    updateProfileInfo("profileUserId", "User ID", data.userId);
    updateProfileInfo("profileEmail", "Email", data.email);
    updateProfileInfo("profilePhone", "Phone", formatPhoneNumber(data.phone));
    updateProfileInfo("profileGender", "Gender", data.gender);
    // updateProfileInfo("profileRole", "Role", data.role);
    document.getElementById("profilePhoto").src = data.photoUrl;
}

function updateProfileInfo(elementId, label, value) {
    const element = document.getElementById(elementId);
    element.innerHTML = `<span class="profile-label">${label}:</span> <span class="profile-value">${value}</span>`;
}

function toggleEditMode(isEditing) {
    const profileInfo = document.getElementById("profileInfo");
    const editForm = document.getElementById("editForm");
    const editButton = document.getElementById("editButton");
    const saveButton = document.getElementById("saveButton");
    const cancelButton = document.getElementById("cancelButton");
    const deleteButton = document.getElementById("deleteButton");
    const changePasswordButton = document.getElementById("changePasswordButton");

    if (isEditing) {
        profileInfo.style.display = "none";
        editForm.classList.add("active");
        editButton.style.display = "none";
        saveButton.style.display = "inline-block";
        cancelButton.style.display = "inline-block";
        deleteButton.style.display = "none";
        changePasswordButton.style.display = "none";

        document.getElementById("editName").value = userData.name;
        document.getElementById("editUserId").value = userData.userId;
        document.getElementById("editEmail").value = userData.email;
        document.getElementById("editPhone").value = userData.phone;
        document.getElementById("editGender").value = userData.gender;
        document.getElementById("editRole").value = userData.role;
    } else {
        profileInfo.style.display = "block";
        editForm.classList.remove("active");
        editButton.style.display = "inline-block";
        saveButton.style.display = "none";
        cancelButton.style.display = "none";
        deleteButton.style.display = "inline-block";
        changePasswordButton.style.display = "inline-block";
    }
}

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}

function validatePhone(phone) {
    const re = /^\d{10}$/;
    return re.test(phone);
}

function formatPhoneNumber(phone) {
    // return `(${phone.slice(0,3)}) ${phone.slice(3,6)}-${phone.slice(6)}`;
    return phone;
}

function saveChanges() {
    const newEmail = document.getElementById("editEmail").value;
    const newPhone = document.getElementById("editPhone").value;
    let isValid = true;

    if (!validateEmail(newEmail)) {
        document.getElementById("emailError").textContent = "Please enter a valid email address.";
        isValid = false;
    } else {
        document.getElementById("emailError").textContent = "";
    }

    if (!validatePhone(newPhone)) {
        document.getElementById("phoneError").textContent = "Please enter a valid 10-digit phone number.";
        isValid = false;
    } else {
        document.getElementById("phoneError").textContent = "";
    }

    if (isValid) {
        userData = {
            ...userData,
            name: document.getElementById("editName").value,
            userId: document.getElementById("editUserId").value,
            email: newEmail,
            phone: newPhone,
            gender: document.getElementById("editGender").value,
            role: document.getElementById("editRole").value
        };
        updateProfile(userData);
        toggleEditMode(false);
    }
}

function showModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

function hideModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

function changePassword() {
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const passwordError = document.getElementById("passwordError");

    // if (currentPassword !== userData.password) {
    //     passwordError.textContent = "Current password is incorrect.";
    //     return;
    // }

    if (newPassword !== confirmPassword) {
        passwordError.textContent = "New passwords do not match.";
        return;
    }

    if (newPassword.length < 8) {
        passwordError.textContent = "New password must be at least 8 characters long.";
        return;
    }

    userData.password = newPassword;
    passwordError.textContent = "";
    alert("Password changed successfully!");
    hideModal("changePasswordModal");
}

function deleteProfile() {
    userData = null;
    const profileCard = document.getElementById("profileCard");
    profileCard.innerHTML = "<h2>Profile Deleted</h2>";
    hideModal("deleteConfirmModal");
    alert("Profile deleted successfully!");
    window.location.href="../Homepages/LoginPage.html";
}

document.getElementById("editButton").addEventListener("click", () => toggleEditMode(true));
document.getElementById("saveButton").addEventListener("click", saveChanges);
document.getElementById("cancelButton").addEventListener("click", () => toggleEditMode(false));
document.getElementById("changePasswordButton").addEventListener("click", () => showModal("changePasswordModal"));
document.getElementById("deleteButton").addEventListener("click", () => showModal("deleteConfirmModal"));
document.getElementById("submitPasswordChange").addEventListener("click", changePassword);
document.getElementById("confirmDelete").addEventListener("click", deleteProfile);
document.getElementById("cancelDelete").addEventListener("click", () => hideModal("deleteConfirmModal"));

document.querySelectorAll(".close").forEach(closeButton => {
    closeButton.addEventListener("click", () => {
        hideModal("changePasswordModal");
        hideModal("deleteConfirmModal");
    });
});

window.onclick = function(event) {
    if (event.target.className === "modal") {
        hideModal("changePasswordModal");
        hideModal("deleteConfirmModal");
    }
}

updateProfile(userData);