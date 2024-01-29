
Модуль 2 - Модуль управления чат-ботом

Пример админ-панели: [https://drive.google.com/file/d/1QxDCK9aHNdIY4DRZIPWdPeauEofOatQf/view?usp=sharing](https://drive.google.com/file/d/17kXQcluO8BZK7WWFeppFbbTwzPZCisX6/view?usp=sharing)

Задачи админ-панели: 
- Редактирование узлов меню (добавление, изменение, удаление)
- Проведение рассылок. 

Структурная схема работы чат-бота в рамках всего проекта.

  ![image](https://github.com/NeCheLoveC/demo-sstu2/assets/81810324/6a461e5f-fad1-4d07-ae72-6b6b1a2bb0be)


ER-диаграмма чат-бота:
![image](https://github.com/NeCheLoveC/demo-sstu2/assets/81810324/2dfff513-90b1-4245-a011-fc0c29d0db51)

―	User – Абитуриенты в ранжированных списках

―	Claim_priorities – это список специальностей по приоритетам, который формируется при подаче заявления на поступление.

―	Claims – это список направлений, на которые абитуриент подал заявку на поступление.

―	Direction – это направления обучения ВУЗа.

―	Institutue – институты в ВУЗе.

―	Score – набранные баллы за вступительный экзамен.

―	Exam – экзамены.

―	Node – редактируемые узлы для меню чат-бота.

―	Telegram_user – авторизированные пользователи Telegram.

―	Meta_info_about_user_into_direction – мета-информация о ранжированных списках.

