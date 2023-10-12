CREATE TABLE payment (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    status TEXT NOT NULL,
    type TEXT NOT NULL,
    value FLOAT DEFAULT 0,
    link TEXT DEFAULT NULL,
    dtype TEXT
);

CREATE TABLE orders (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    customer_id TEXT DEFAULT NULL references customer(id),
    status TEXT NOT NULL DEFAULT 'OPEN',
    install_date TIMESTAMP DEFAULT NULL,
    postal_code TEXT DEFAULT NULL,
    street TEXT DEFAULT NULL,
    number TEXT DEFAULT NULL,
    complement TEXT DEFAULT NULL,
    neighborhood TEXT DEFAULT NULL,
    city TEXT DEFAULT NULL,
    state TEXT DEFAULT NULL,
    profit_margin FLOAT DEFAULT 0,
    discount FLOAT DEFAULT 0,
    payment_id TEXT DEFAULT NULL references payment(id)
);

CREATE TABLE order_product (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    order_id TEXT DEFAULT NULL references orders(id),
    product_id TEXT NOT NULL references product(id),
    quantity INT NOT NULL DEFAULT 1,
    height FLOAT DEFAULT NULL,
    width FLOAT DEFAULT NULL,
    unit_value FLOAT NOT NULL,
    total_value FLOAT NOT NULL
);

ALTER TABLE payment ADD COLUMN order_id TEXT DEFAULT NULL references orders(id);
