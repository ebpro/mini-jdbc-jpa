CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE animators (
                           id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE radio_program_grid (
                                    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                    program_name VARCHAR(255) NOT NULL,
                                    animator_id UUID NOT NULL,
                                    start_time TIME NOT NULL,
                                    end_time TIME NOT NULL,
                                    day_of_week VARCHAR(10) NOT NULL,
                                    FOREIGN KEY (animator_id) REFERENCES animators(id)
);

INSERT INTO animators (id, name, email) VALUES
                                            (uuid_generate_v4(), 'Alice', 'alice@example.com'),
                                            (uuid_generate_v4(), 'Bob', 'bob@example.com'),
                                            (uuid_generate_v4(), 'Charlie', 'charlie@example.com');

INSERT INTO radio_program_grid (id, program_name, animator_id, start_time, end_time, day_of_week) VALUES
                                                                                                      (uuid_generate_v4(), 'Morning Show', (SELECT id FROM animators WHERE name = 'Alice'), '06:00:00', '09:00:00', 'Monday'),
                                                                                                      (uuid_generate_v4(), 'Morning Show', (SELECT id FROM animators WHERE name = 'Alice'), '06:00:00', '09:00:00', 'Tuesday'),
                                                                                                      (uuid_generate_v4(), 'Morning Show', (SELECT id FROM animators WHERE name = 'Alice'), '06:00:00', '09:00:00', 'Wednesday'),
                                                                                                      (uuid_generate_v4(), 'Morning Show', (SELECT id FROM animators WHERE name = 'Alice'), '06:00:00', '09:00:00', 'Thursday'),
                                                                                                      (uuid_generate_v4(), 'Morning Show', (SELECT id FROM animators WHERE name = 'Alice'), '06:00:00', '09:00:00', 'Friday'),
                                                                                                      (uuid_generate_v4(), 'News Hour', (SELECT id FROM animators WHERE name = 'Bob'), '09:00:00', '10:00:00', 'Monday'),
                                                                                                      (uuid_generate_v4(), 'News Hour', (SELECT id FROM animators WHERE name = 'Bob'), '09:00:00', '10:00:00', 'Tuesday'),
                                                                                                      (uuid_generate_v4(), 'News Hour', (SELECT id FROM animators WHERE name = 'Bob'), '09:00:00', '10:00:00', 'Wednesday'),
                                                                                                      (uuid_generate_v4(), 'News Hour', (SELECT id FROM animators WHERE name = 'Bob'), '09:00:00', '10:00:00', 'Thursday'),
                                                                                                      (uuid_generate_v4(), 'News Hour', (SELECT id FROM animators WHERE name = 'Bob'), '09:00:00', '10:00:00', 'Friday'),
                                                                                                      (uuid_generate_v4(), 'Evening Jazz', (SELECT id FROM animators WHERE name = 'Charlie'), '18:00:00', '20:00:00', 'Monday'),
                                                                                                      (uuid_generate_v4(), 'Evening Jazz', (SELECT id FROM animators WHERE name = 'Charlie'), '18:00:00', '20:00:00', 'Wednesday'),
                                                                                                      (uuid_generate_v4(), 'Evening Jazz', (SELECT id FROM animators WHERE name = 'Charlie'), '18:00:00', '20:00:00', 'Friday');