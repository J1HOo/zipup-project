
/* ********************** 상품 리스트 관련********************** */

    document.addEventListener("DOMContentLoaded", () => {
    const sliderContainer = document.querySelector(".slider-container");

    let isDown = false;
    let startX;
    let scrollLeft;

    sliderContainer.addEventListener("mousedown", (e) => {
    isDown = true;
    sliderContainer.classList.add("active");
    startX = e.pageX - sliderContainer.offsetLeft;
    scrollLeft = sliderContainer.scrollLeft;
});

    sliderContainer.addEventListener("mouseleave", () => {
    isDown = false;
    sliderContainer.classList.remove("active");
});

    sliderContainer.addEventListener("mouseup", () => {
    isDown = false;
    sliderContainer.classList.remove("active");
});

    sliderContainer.addEventListener("mousemove", (e) => {
    if (!isDown) return;
    e.preventDefault();
    const x = e.pageX - sliderContainer.offsetLeft;
    const walk = (x - startX) * 2; // 스크롤 속도 조정
    sliderContainer.scrollLeft = scrollLeft - walk;
});
});