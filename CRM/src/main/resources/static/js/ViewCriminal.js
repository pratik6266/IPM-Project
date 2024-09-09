const criminals = [
    {
        id: 1,
        name: "Criminal 1",
        photo: "",
        dna: "DNA 1",
        gender: "Male",
        photos: ["", "", ""],
        retina: "",
        fingerprint: "",
        crimeDetails: "Sample Crime Type and Details"
    },
    {
        id: 2,
        name: "Criminal 2",
        photo: "",
        dna: "DNA 2",
        gender: "Female",
        photos: ["", "", ""],
        retina: "",
        fingerprint: "",
        crimeDetails: "Sample Crime Type and Details"
    },
    {
        id: 3,
        name: "Criminal 3",
        photo: "",
        dna: "DNA 3",
        gender: "Male",
        photos: ["", "", ""],
        retina: "",
        fingerprint: "",
        crimeDetails: "Sample Crime Type and Details"
    }
];

const criminalList = document.getElementById('criminal-list');
const modal = document.getElementById('modal');
const closeBtn = document.getElementsByClassName('close')[0];
const criminalDetails = document.getElementById('criminal-details');

function createCriminalCard(criminal) {
    const card = document.createElement('div');
    card.classList.add('criminal-card');
    card.innerHTML = `
        <img src="../../images/criminal.jpg" alt="../../images/two hand.webp" >
        <h3>${criminal.name}</h3>
    `;
    card.addEventListener('click', () => showCriminalDetails(criminal));
    return card;
}

function showCriminalDetails(criminal) {
    criminalDetails.innerHTML = `
        <h2>${criminal.name}</h2>
        <div class="info-section">
            <h3>Basic Information</h3>
            <p><strong>DNA:</strong> ${criminal.dna}</p>
            <p><strong>Gender:</strong> ${criminal.gender}</p>
            <p><strong>Crime Details:</strong> ${criminal.crimeDetails}</p>
        </div>
        <div class="info-section">
            <h3>Photos</h3>
            <div class="photo-grid">
                <img src="../../images/angle1.jpeg" alt="Photo 1 of ${criminal.name}">
                <img src="../../images/angle2.jpg" alt="Photo 2 of ${criminal.name}">
                <img src="../../images/angle3.webp" alt="Photo 3 of ${criminal.name}">
            </div>
        </div>
        <div class="info-section">
            <h3>Retina Scan</h3>
            <img src="../../images/retina.png" alt="Retina Scan of ${criminal.name}">
        </div>
        <div class="info-section">
            <h3>Fingerprint</h3>
            <img src="../../images/two hand.webp" alt="Fingerprint of ${criminal.name}">
        </div>
    `;
    modal.style.display = 'block';
}

criminals.forEach(criminal => {
    const card = createCriminalCard(criminal);
    criminalList.appendChild(card);
});

closeBtn.onclick = function() {
    modal.style.display = 'none';
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}