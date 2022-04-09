db = db.getSiblingDB('db_sgo')
db.createUser(
    {
        user: "user_sgo",
        pwd: "Sg0p@SsWd",
        roles: [
            { role: "readWrite", db: "db_sgo" }
        ]
    }
)