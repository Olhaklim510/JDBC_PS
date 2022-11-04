CREATE TABLE IF NOT EXISTS worker (
    id IDENTITY PRIMARY KEY,
    name VARCHAR (1000) NOT NULL CHECK (CHAR_LENGTH (name) >1), 
    birthday DATE NOT NULL CHECK (birthday >= (DATE '1900-01-01')),
    level VARCHAR (10) NOT NULL CHECK level IN ('Trainee', 'Junior', 'Middle', 'Senior'),
    salary NUMERIC NOT NULL CHECK salary>100 AND salary<100000
); 

CREATE TABLE IF NOT EXISTS client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR (1000) NOT NULL CHECK (CHAR_LENGTH (name) >1)  
);  

CREATE TABLE IF NOT EXISTS project (
    id IDENTITY PRIMARY KEY,
    client_id BIGINT NOT NULL,
    start_date DATE,
    finish_date DATE,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS project_worker (
    project_id BIGINT NOT NULL,
    worker_id BIGINT NOT NULL,
    PRIMARY KEY (project_id,worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);