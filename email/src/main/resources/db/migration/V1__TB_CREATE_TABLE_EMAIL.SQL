CREATE TABLE IF NOT EXISTS TB_EMAIL (
    email_Id UUID PRIMARY KEY,
    email_From VARCHAR(255),
    email_To VARCHAR(255),
    email_Subject VARCHAR(255),
    body TEXT,
    send_Date_Email DATE,
    status_Email VARCHAR(50)
);