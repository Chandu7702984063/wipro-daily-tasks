const images = [
    "https://picsum.photos/id/1015/300/200",
    "https://picsum.photos/id/1016/300/200",
    "https://picsum.photos/id/1025/300/200",
    "https://picsum.photos/id/1035/300/200"
];

let currentIndex = 0;

function showImage(index) {
    document.getElementById("galleryImg").src = images[index];
}

document.getElementById("prevBtn").addEventListener("click", function() {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    showImage(currentIndex);
});

document.getElementById("nextBtn").addEventListener("click", function() {
    currentIndex = (currentIndex + 1) % images.length;
    showImage(currentIndex);
});

// Show first image initially
showImage(currentIndex);