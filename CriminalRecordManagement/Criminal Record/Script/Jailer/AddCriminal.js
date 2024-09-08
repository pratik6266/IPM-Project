document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('criminalForm');
    const fileInputs = document.querySelectorAll('input[type="file"]');

    fileInputs.forEach(input => {
        input.addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    const label = event.target.nextElementSibling;
                    label.innerHTML = '';
                    label.appendChild(img);
                }
                reader.readAsDataURL(file);
            }
        });
    });

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        // Simulate form submission
        const submitButton = this.querySelector('button[type="submit"]');
        submitButton.textContent = 'Submitting...';
        submitButton.disabled = true;

        setTimeout(() => {
            alert('Criminal data submitted successfully!');
            form.reset();
            // Reset file input previews
            fileInputs.forEach(input => {
                const label = input.nextElementSibling;
                label.innerHTML = `
                    <svg class="icon" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12"></path></svg>
                    <span class="text">Upload ${input.id === 'retina' ? 'retina scan' : input.id === 'fingerprint' ? 'fingerprint' : 'photo'}</span>
                `;
            });
            submitButton.textContent = 'Submit';
            submitButton.disabled = false;
            window.location.href="JailerHomePage.html";
        }, 2000);
    });
});