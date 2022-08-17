export const userErrorMessage = Object.freeze({ 
    loginError: '아이디 혹은 비밀번호가 옳지 않습니다', 
    notMember: '없는 회원정보입니다',
    passwordMatchError: '비밀번호가 일치하지 않습니다',
    passwordValidityError: '비밀번호 형식이 올바르지 않습니다. 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다. ',
    emailError: '이메일 형식이 올바르지 않습니다',
    nicknameError: '2~10자의 한글, 영문, 숫자만 사용할 수 있습니다',
    phoneError: '핸드폰 번호 형식이 올바르지 않습니다. 반드시 하이픈(-)을 추가해주세요.',
    alreadyRegistered: '이미 가입이 된 이메일입니다',
    wrongVerificationCode: '인증번호가 일치하지 않습니다',
})

export const badgeNames = Object.freeze({ 
    firstSignUp: '여행의 시작', 
    firstDiary: '기록의 시작',
    firstPost: '만남의 시작',
})