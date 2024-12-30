const fileInput = document.getElementById('image'); // fileInput 선언
const productImage = document.getElementById('product-image');
const preview = document.getElementById('preview');
const instruction = document.getElementById('instruction');

/* **********************썸네일(image) 이미지 관련********************** */

// 이미지 박스 클릭 시 파일 선택 창 열기
productImage.addEventListener('click', function () {
    fileInput.click();
});

// 파일 선택 시 이미지 출력
fileInput.addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();

        reader.onload = function (e) {
            // 기존 이미지와 텍스트 제거
            preview.innerHTML = "";
            instruction.style.display = "none"; // 텍스트 숨김

            // 새로운 이미지 추가
            const img = document.createElement('img');
            img.src = e.target.result;
            img.alt = "새로운 이미지 업로드"
            preview.appendChild(img);
        };

        reader.readAsDataURL(file);
    }
});

/* **********************상세이미지(description) 이미지 관련********************** */


const descriptionImage = document.getElementById('description-image');
const descriptionFileInput = document.getElementById('description');
const descriptionPreview = document.getElementById('description-preview');

// 이미지 박스 클릭 시 파일 선택 창 열기
descriptionImage.addEventListener('click', function (){
  descriptionFileInput.click()
});

// 파일 선택 시 이미지 출력
descriptionFileInput.addEventListener('change', function (event){
   const file2 = event.target.files[0];
   if(file2){
       const reader2 = new FileReader();

       reader2.onload = function (e){
           //기존 내용 비우고 새로운 이미지 추가
           descriptionPreview.innerHTML = "";

           const descriptionImg = document.createElement('img')
           descriptionImg.src = e.target.result;
           descriptionPreview.appendChild(descriptionImg);

           //미리보기 영역 표시
           descriptionPreview.style.display = "block";
           descriptionPreview.removeAttribute('hidden');
       };
       reader2.readAsDataURL(file2);
   }
});