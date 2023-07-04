package com.springstudy.study.homework.DZ7.constants;

public interface MailConstants {
    String MAIL_MESSAGE_FOR_REMEMBER_PASSWORD = """
          Добрый день. Вы получили это письмо, так как с вашего аккаунта была отправлена заявка на восстановление пароля.\n
          Для восстановления пароля перейдите по ссылке: http://localhost:8081/users/change-password?uuid=""";

    String MAIL_SUBJECT_FOR_REMEMBER_PASSWORD = "Восстановление пароля на сайте Онлайн Библиотека";
}

