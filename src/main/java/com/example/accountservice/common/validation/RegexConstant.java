package com.example.accountservice.common.validation;

public interface RegexConstant {
    String VI_TEXT_REGEX = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝỲỶỴàáâãèéêìíòóôõùúýỳỷỵĂăĐđẰằẦầỀềỒồỜờỪừẤấẮắẾếỐốỚớỨứẢảẲẳẨẩẺẻỂểỈỉỎỏỔổỞởỦủỬửẴẵẪẫẼẽỄễĨĩỠỡỖỗŨũỮữƠơƯưẠạẶặẬậẸẹỆệỊịỌọỘộỢợỤụỰự-Ỹỹ _,.-]*";
    String VI_TEXT_AND_SPECIAL_CHARACTERS = "[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝỲỶỴàáâãèéêìíòóôõùúýỳỷỵĂăĐđẰằẦầỀềỒồỜờỪừẤấẮắẾếỐốỚớỨứẢảẲẳẨẩẺẻỂểỈỉỎỏỔổỞởỦủỬửẴẵẪẫẼẽỄễĨĩỠỡỖỗŨũỮữƠơƯưẠạẶặẬậẸẹỆệỊịỌọỘộỢợỤụỰự-Ỹỹ _,.\\\\+():;|/-]*";
    String EMAIL = "^\\S+@\\S+.\\S+$";
    String NORMAL_TEXT = "[a-zA-Z0-9_.-]*";
    String EMPTY_OR = "^$|";
    String VI_ONLY_ALPHABET = "[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝỲỶỴàáâãèéêìíòóôõùúýỳỷỵĂăĐđẰằẦầỀềỒồỜờỪừẤấẮắẾếỐốỚớỨứẢảẲẳẨẩẺẻỂểỈỉỎỏỔổỞởỦủỬửẴẵẪẫẼẽỄễĨĩỠỡỖỗŨũỮữƠơƯưẠạẶặẬậẸẹỆệỊịỌọỘộỢợỤụỰự-Ỹỹ ]*";
    String PHONE_FE_OPS = "^\\+?\\d{9,11}$";
    String PHONE_BE_OPS = "^0+[0-9]*";
    String PHONE_REGEX = "^(0[1-9])+([0-9]{8})\\b";
}
