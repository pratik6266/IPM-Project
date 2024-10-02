async function checkUsernameAvailability() {
    const username = document.getElementById('username').value;
    const feedback = document.getElementById('usernameFeedback');

    if (username.length > 0) {
        try {
            const response = await fetch(`/api/check-username?username=${username}`);
            const result = await response.json();

            if (result.exists) {
                feedback.textContent = 'Username is already taken';
                feedback.style.color = 'red';
            } else {
                feedback.textContent = 'Username is available';
                feedback.style.color = 'green';
            }
        } catch (error) {
            console.error('Error checking username:', error);
        }
    } else {
        feedback.textContent = '';
    }
}

function checkPasswordStrength() {
    const password = document.getElementById('password').value;
    const feedback = document.getElementById('passwordFeedback');

    if (password.length < 8) {
        feedback.textContent = 'Password too short. Minimum 8 characters required.';
        feedback.style.color = 'red';
    } else if (!/[A-Z]/.test(password) || !/[a-z]/.test(password) || !/[0-9]/.test(password)) {
        feedback.textContent = 'Password should include uppercase, lowercase, and numbers.';
        feedback.style.color = 'red';
    } else {
        feedback.textContent = 'Password is strong.';
        feedback.style.color = 'green';
    }
}

function validatePasswordMatch() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const feedback = document.getElementById('passwordMatchFeedback');

    if (password !== confirmPassword) {
        feedback.textContent = 'Passwords do not match.';
        feedback.style.color = 'red';
    } else if (password.length === 0 || confirmPassword.length === 0) {
        feedback.textContent = '';
    } else {
        feedback.textContent = 'Passwords match.';
        feedback.style.color = 'green';
    }
}
