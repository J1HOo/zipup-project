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
descriptionImage.addEventListener('click', function () {
    descriptionFileInput.click()
});

// 파일 선택 시 이미지 출력
descriptionFileInput.addEventListener('change', function (event) {
    const file2 = event.target.files[0];
    if (file2) {
        const reader2 = new FileReader();

        reader2.onload = function (e) {
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


/* **********************옵션(색상) 추가 관련********************** */
document.addEventListener('DOMContentLoaded', () => {
    const colorContainer = document.querySelector('#color-option-container');

    // 차단할 키 목록 (쉼표, 마침표, 하이픈, 등호, 물결표, 슬래시, 더하기)
    const blockedKeys = [',', '.', '-', '=', '~', '/', '+', ' '];

    // 입력을 차단하는 함수
    const preventBlockedKeys = (event) => {
        if (blockedKeys.includes(event.key)) {
            event.preventDefault(); // 차단된 키 입력을 막음
        }
    };

    // 색상 입력창에 차단할 키 입력 막기
    colorContainer.addEventListener('input', (e) => {
        if (e.target.classList.contains('seller-optionColor')) {
            e.target.addEventListener('keydown', preventBlockedKeys);
        }
    });

    // 플러스 버튼 클릭 이벤트
    colorContainer.addEventListener('click', (e) => {
        // 플러스 버튼 클릭 시 새로운 옵션 추가
        if (e.target.closest('.seller-option-add-button1')) {
            const addColorOption = document.createElement('div');
            addColorOption.className = 'color-option-input';
            addColorOption.innerHTML = `
                <input type="text" name="option1" class="seller-optionColor" placeholder="색상을 하나씩 입력해 주세요">
                <button type="button" class="seller-option-remove-button">
                    <span><img src="/images/svg/minus.svg" class="remove-option-icon"></span>
                </button>`;
            colorContainer.appendChild(addColorOption);
        }

        // 마이너스 버튼 클릭 시 삭제
        if (e.target.closest('.seller-option-remove-button')) {
            e.target.closest('.color-option-input').remove();
        }
    });
});

/* **********************옵션(사이즈) 추가 관련********************** */
document.addEventListener('DOMContentLoaded', () => {
    const sizeContainer = document.querySelector('#size-option-container');

    // 차단할 키 목록 (쉼표, 마침표, 하이픈, 등호, 물결표, 슬래시, 더하기)
    const blockedKeys = [',', '.', '-', '=', '~', '/', '+', ' '];

    // 입력을 차단하는 함수
    const preventBlockedKeys = (event) => {
        if (blockedKeys.includes(event.key)) {
            event.preventDefault(); // 차단된 키 입력을 막음
        }
    };

    // 사이즈 입력창에 차단할 키 입력 막기
    sizeContainer.addEventListener('input', (e) => {
        if (e.target.classList.contains('seller-optionSize')) {
            e.target.addEventListener('keydown', preventBlockedKeys);
        }
    });

    //플러스 버튼 클릭 이벤트
    sizeContainer.addEventListener('click', (e) => {
        // 플러스 버튼 클릭 시 새로운 옵션 추가
        if(e.target.closest('.seller-option-add-button2')){
            const addSizeOption = document.createElement('div');
            addSizeOption.className = 'size-option-input';
            addSizeOption.innerHTML = `
                    <input type="text" id="option2" name="option2" class="seller-optionSize" placeholder="사이즈를 하나씩 입력해 주세요"/>
                    <button type="button" class="seller-option-remove-button">
                        <span><img src="/images/svg/minus.svg" class="remove-option-icon"></span>
                    </button>`
            sizeContainer.appendChild(addSizeOption);
        }

        //마이너스 버튼 클릭 시 삭제
        if (e.target.closest('.seller-option-remove-button')){
            e.target.closest('.size-option-input').remove();
        }
    });

});

/* **********************옵션 없음 버튼 관련********************** */

document.addEventListener('DOMContentLoaded', () => {
    const option3Button = document.getElementById('option3');
    const colorContainer = document.getElementById('color-option-container');
    const sizeContainer = document.getElementById('size-option-container');

    const colorAddButton = colorContainer.querySelector('.seller-option-add-button1');
    const sizeAddButton = sizeContainer.querySelector('.seller-option-add-button2');

    // "옵션 없음" 버튼 클릭 이벤트
    option3Button.addEventListener('click', () => {
        const colorInputs = colorContainer.querySelectorAll('.seller-optionColor');
        const sizeInputs = sizeContainer.querySelectorAll('.seller-optionSize');
        const colorOptionDivs = colorContainer.querySelectorAll('.color-option-input');
        const sizeOptionDivs = sizeContainer.querySelectorAll('.size-option-input');

        // 클릭 시, 상태 전환 (비활성화/활성화)
        const isDisabled = colorInputs[0].disabled; // 첫 번째 색상 입력창의 disabled 상태를 확인

        if (isDisabled) {
            // 활성화
            colorInputs.forEach(input => input.disabled = false);
            sizeInputs.forEach(input => input.disabled = false);
            colorAddButton.disabled = false;
            sizeAddButton.disabled = false;

            // 활성화 스타일 제거
            colorInputs.forEach(input => input.classList.remove('option-disabled'));
            sizeInputs.forEach(input => input.classList.remove('option-disabled'));
            colorAddButton.classList.remove('option-disabled');
            sizeAddButton.classList.remove('option-disabled');

            // 색상 복원
            option3Button.classList.remove('color-change'); // 색상 복원
            option3Button.classList.add('color-restoration'); // 원래 색상 복원
        } else {
            // 비활성화
            colorInputs.forEach(input => input.value = '');  // 색상 옵션 초기화
            sizeInputs.forEach(input => input.value = '');  // 사이즈 옵션 초기화

            // 추가된 옵션들 삭제
            colorOptionDivs.forEach(option => option.remove());
            sizeOptionDivs.forEach(option => option.remove());

            // 입력 창, 버튼 비활성화
            colorInputs.forEach(input => input.disabled = true);
            sizeInputs.forEach(input => input.disabled = true);
            colorAddButton.disabled = true;
            sizeAddButton.disabled = true;

            // 비활성화 스타일 추가
            colorInputs.forEach(input => input.classList.add('option-disabled'));
            sizeInputs.forEach(input => input.classList.add('option-disabled'));
            colorAddButton.classList.add('option-disabled');
            sizeAddButton.classList.add('option-disabled');

            // 버튼 색상 변경
            option3Button.classList.remove('color-restoration'); // 색상 원래로 복원
            option3Button.classList.add('color-change'); // 비활성화 색상 변경
        }
    });
});
