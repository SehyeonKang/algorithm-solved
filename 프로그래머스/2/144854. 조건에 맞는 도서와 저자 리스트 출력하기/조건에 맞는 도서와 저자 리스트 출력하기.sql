SELECT B.BOOK_ID, A.AUTHOR_NAME, DATE_FORMAT(B.PUBLISHED_DATE, '%Y-%m-%d')
FROM BOOK B JOIN AUTHOR A
USING (AUTHOR_ID)
WHERE CATEGORY = '경제'
ORDER BY B.PUBLISHED_DATE ASC;