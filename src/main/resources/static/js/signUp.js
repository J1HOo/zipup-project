let isEmailChecked = false; // 이메일 중복 확인 여부
document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const userNameInput = document.getElementById("userName");
    const userForm = document.querySelector(".signup-user-form");

    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*+=-])[A-Za-z\d!@#$%^&*+=-]{8,}$/;
    const namePattern = /^[A-Za-z가-힣]+$/;

    // 폼 제출 시 비밀번호 및 이름 검사
    userForm.addEventListener("submit", function (event) {
        const password = passwordInput.value;
        const userName = userNameInput.value;

        //비밀번호 검증
        if (!passwordPattern.test(password)) {
            alert("비밀번호는 영문, 숫자, 특수문자 조합으로 8자리 이상이어야 합니다.");
            event.preventDefault(); // 폼 제출 방지
        }

        // 이름 검증
        if (!namePattern.test(userName)) {
            alert("이름은 영문 또는 한글만 입력할 수 있으며, 숫자는 포함할 수 없습니다.");
            event.preventDefault(); // 폼 제출 방지
        }

        // 이메일 중복 확인 여부 체크
        if (!isEmailChecked) {
            alert("이메일 중복 확인을 먼저 해주세요.");
            event.preventDefault(); // 폼 제출 방지
        }
    });
});


/******************** 아이디(이메일) 중복************************************************/

document.addEventListener("DOMContentLoaded", function () {
    const userCheckEmailBtn = document.getElementById("check-email-btn");
    const emailInput = document.getElementById("email");
    const errorMessage = document.querySelector(".signup-error-message p");

    if (errorMessage) {
        // 다른 곳을 클릭하면 에러 메시지를 숨김
        document.addEventListener("click", function (event) {
            // 클릭한 요소가 에러 메시지가 아닐 경우 숨김
            if (!errorMessage.contains(event.target)) {
                errorMessage.style.display = "none";
            }
        });
    }

    userCheckEmailBtn.addEventListener("click", function () {
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
                    isEmailChecked = false; // 중복 확인 실패
                } else {
                    alert("사용 가능한 이메일입니다.");
                    isEmailChecked = true; // 중복 확인 성공
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("이메일 중복 확인 중 문제가 발생했습니다.");
            });
    });
});

/********************  판매자 아이디(이메일) 중복 확인 필수 여부*********************************/
let sellerEmailChecked = false; // 이메일 중복 확인 여부
document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const form = document.querySelector(".signup-form");
    const checkEmailBtn = document.getElementById("check-email-btn");

    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*+=-])[A-Za-z\d!@#$%^&*+=-]{8,}$/;

    checkEmailBtn.addEventListener("click", function () {
        const email = emailInput.value;

        // 이메일 패턴 체크
        if (!emailPattern.test(email)) {
            alert("이메일 형식이 올바르지 않습니다.");
            return;
        }

        // 서버에 이메일 중복 확인 요청
        fetch(`/check-email?email=${email}`)
            .then(response => response.json())
            .then(data => {
                if (data.isTaken) {
                    sellerEmailChecked = false; // 이메일 중복
                } else {
                    sellerEmailChecked = true; // 이메일 사용 가능
                }
            })
            .catch(error => {
                alert("이메일 중복 확인에 실패했습니다.");
                sellerEmailChecked = false; // 실패 시 중복 확인 안됨
            });
    });

    // 폼 제출 시 비밀번호 및 이메일 검사
    form.addEventListener("submit", function (event) {
        const email = emailInput.value;
        const password = passwordInput.value;

        // 이메일 검증
        if (!emailPattern.test(email)) {
            alert("이메일은 이메일 형식으로 영문자 및 숫자 조합으로 제출해주세요.");
            event.preventDefault(); // 폼 제출 방지
        }

        //비밀번호 검증
        if (!passwordPattern.test(password)) {
            alert("비밀번호는 영문, 숫자, 특수문자 조합으로 8자리 이상이어야 합니다.");
            event.preventDefault(); // 폼 제출 방지
        }

        // 이메일 중복 확인 여부 체크
        if (!sellerEmailChecked) {
            alert("이메일 중복 확인을 먼저 해주세요.");
            event.preventDefault(); // 폼 제출 방지
        }
    });
});