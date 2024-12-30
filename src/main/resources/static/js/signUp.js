document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const form = document.querySelector(".signup-form");

    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*+=-])[A-Za-z\d!@#$%^&*+=-]{8,}$/;


    // 폼 제출 시 비밀번호 검사
    form.addEventListener("submit", function (event) {
        const password = passwordInput.value;

        if (!passwordPattern.test(password)) {
            alert("비밀번호는 영문, 숫자, 특수문자 조합으로 8자리 이상이어야 합니다.");
            event.preventDefault(); // 폼 제출 방지
        }
    });
});

/******************** 아이디(이메일) 중복************************************************/

document.addEventListener("DOMContentLoaded", function () {
    const checkEmailBtn = document.getElementById("check-email-btn");
    const emailInput = document.getElementById("email");
    const errorMessage = document.querySelector(".signup-error-message p");

    if (errorMessage) {
        // 다른 곳을 클릭하면 에러 메시지를 숨김
        document.addEventListener("click", function (event) {
            // 클릭한 요소가 에러 메시지가 아닐 경우 숨김
            if (!errorMessage.contains(event.target)) {
                errorMessage.style.display = "none";
            }
        })
    }
    checkEmailBtn.addEventListener("click", function () {
        const email = emailInput.value.trim();

        if (!email) {
            alert("이메일을 입력하세요.");
            return;
        }

        // 서버로 이메일 중복 확인 요청
        fetch(`/check-email?email=${encodeURIComponent(email)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("서버 응답 실패");
                }
                return response.json();
            })
            .then(data => {
                if (data.isTaken) {
                    alert("중복된 이메일입니다. 다른 이메일을 입력하세요.");
                } else {
                    alert("사용 가능한 이메일입니다.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("이메일 중복 확인 중 문제가 발생했습니다.");
            });
    });
});

