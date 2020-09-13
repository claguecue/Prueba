
--------------------------------------------------------------------------------
--                             ACCOUNTS                                    --
--------------------------------------------------------------------------------

INSERT INTO account(id,name,currency,balance,treasury) VALUES (1, 'Maria Munoz', 'EUR', 200.0, true);
INSERT INTO account(id,name,currency,balance,treasury) VALUES (2, 'Lucia Cuesta', 'EUR', 50.0, false);
INSERT INTO account(id,name,currency,balance,treasury) VALUES (3, 'Juan Palomares', 'EUR', 10000.0, false);


--------------------------------------------------------------------------------
--                               TRANSFERS                                   --
--------------------------------------------------------------------------------

INSERT INTO transfer(id,quantity,account_from_id,account_to_id) VALUES (1, 100.00, 1, 2);
INSERT INTO transfer(id,quantity,account_from_id,account_to_id) VALUES (2, 30.00, 3, 1);