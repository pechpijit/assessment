DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS user_ticket;

CREATE TABLE lottery
(
    ticket_number VARCHAR(6) UNIQUE NOT NULL PRIMARY KEY,
    price         INT               NOT NULL,
    amount        INT               NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE users
(
    user_id    VARCHAR(10) UNIQUE NOT NULL PRIMARY KEY,
    username   VARCHAR(100)       NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE user_ticket
(
    ticket_id     SERIAL PRIMARY KEY,
    ticket_number VARCHAR(6)  NOT NULL,
    user_id       VARCHAR(10) NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    FOREIGN KEY (ticket_number) REFERENCES lottery (ticket_number),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE INDEX idx_ticket_number ON lottery (ticket_number);
CREATE INDEX idx_user_id ON user_ticket (user_id);

INSERT INTO users(user_id, username) VALUES ('1111111111', 'PechyEiEi#11111');
INSERT INTO users(user_id, username) VALUES ('2222222222', 'PechyEiEi#22222');
INSERT INTO users(user_id, username) VALUES ('3333333333', 'PechyEiEi#33333');
INSERT INTO users(user_id, username) VALUES ('4444444444', 'PechyEiEi#44444');
INSERT INTO users(user_id, username) VALUES ('5555555555', 'PechyEiEi#55555');

INSERT INTO lottery(ticket_number, price, amount) VALUES ('111111', 80, 1);
INSERT INTO lottery(ticket_number, price, amount) VALUES ('222222', 80, 1);
INSERT INTO lottery(ticket_number, price, amount) VALUES ('333333', 80, 1);
INSERT INTO lottery(ticket_number, price, amount) VALUES ('444444', 80, 1);
INSERT INTO lottery(ticket_number, price, amount) VALUES ('555555', 80, 1);