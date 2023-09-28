CREATE TABLE merchant (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    name TEXT NOT NULL,
    email TEXT DEFAULT NULL,
    document_number TEXT DEFAULT NULL,
    photo TEXT DEFAULT NULL
);

CREATE TABLE users (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    merchant_id TEXT DEFAULT NULL REFERENCES merchant(id),
    first_name TEXT DEFAULT NULL,
    last_name TEXT NOT NULL,
    postal_code TEXT DEFAULT NULL,
    street TEXT DEFAULT NULL,
    number TEXT DEFAULT NULL,
    complement TEXT DEFAULT NULL,
    neighborhood TEXT DEFAULT NULL,
    city TEXT DEFAULT NULL,
    state TEXT DEFAULT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    phone TEXT DEFAULT NULL,
    photo TEXT DEFAULT NULL,
    role TEXT DEFAULT 'user'
);
