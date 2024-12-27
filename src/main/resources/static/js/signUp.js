

/*--------------------------- userSignup script   -------------------------------*/
document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const errorMsg = document.querySelector(".userSignup-error-msg");
    const form = document.querySelector(".signup-form");

    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*+=-])[A-Za-z\d!@#$%^&*+=-]{8,}$/;

    // 비밀번호 입력 시 실시간 검사
    passwordInput.addEventListener("input", function () {
        const password = passwordInput.value;

        if (!passwordPattern.test(password)) {
            errorMsg.style.display = "block";
        } else {
            errorMsg.style.display = "none";
        }
    });

    // 비밀번호 입력 필드가 포커스를 잃으면 메시지 숨기기
    passwordInput.addEventListener("blur", function () {
        errorMsg.style.display = "none";
    });


    // 폼 제출 시 비밀번호 검사
    form.addEventListener("submit", function (event) {
        const password = passwordInput.value;

        if (!passwordPattern.test(password)) {
            alert("비밀번호는 영문, 숫자, 특수문자 조합으로 8자리 이상이어야 합니다.");
            event.preventDefault(); // 폼 제출 방지
        }
    });
});