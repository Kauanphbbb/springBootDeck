# API de Deck (host = http://localhost:8080)

# Players

1. Post
    1. host/players
        - Cadastra um novo jogador
        - Body:
        
        ```json
        {
            "nickname": "kauanphbbb"
        }
        ```
        
    2. host/players/addToDeck/{player_id}
        - Adiciona uma carta para um deck selecionado
        - Body:
        
        ```json
        {
            "card_id": "d92eb8ab-ebbb-4152-a38b-b31cdbf573d4",
            "deck_id": "2211b1a2-9e62-4f8b-8a85-21f14b57ef32"
        }
        ```
        
    3. host/players/{player_id}
        - Adiciona uma card para o usuário.
        - Body:
        
        ```json
        {
            "card_id": "38be0de8-e33b-45e4-8877-9cba8e785a04"
        }
        ```
        
2. Delete
    1. host/players/removeFromDeck/{player_id}
        - Remove uma carta do deck selecionado
        - Body:
        
        ```json
        {
            "card_id": "d92eb8ab-ebbb-4152-a38b-b31cdbf573d4",
            "deck_id": "2211b1a2-9e62-4f8b-8a85-21f14b57ef32"
        }
        ```
        
3. Patch
    1. host/players/{player_id}
        - Atualiza o nickname do jogador
        - Body
        
        ```json
        {
            "nickname": "kauanphbbb"
        }
        ```
        
4. Get
    1. host/players
        - Retorna todos os usuários cadastrados
    2. host/players/{player_id}
        - Retorna o usuário com o id passado

# Decks

1. Post
    1. host/decks
        - Cadastra um novo deck
        - body:
    
    ```json
    {
        "deckName": "Deck de cavaleiros 2",
        "player_id": "1fcdcc60-d7ce-4e2f-96f0-14ef60c0f7c0"
    }
    ```
    
2. Delete
    1. host/decks/{deck_id}
        - Deleta um deck
        - body:
    
    ```json
    {
        "playerId": "7eb04380-8a25-45b8-a135-273fd2d85e30"
    }
    ```
    
3. Patch
    1. host/decks/{deck_id}
        - Atualiza o nome do deck
        - Body:
        
        ```json
        {
            "deckName": "Deck de magos 2",
            "player_id": "1fcdcc60-d7ce-4e2f-96f0-14ef60c0f7c0"
        }
        ```
        
4. Get
    1. host/decks
        - Retorna todos os decks cadastrados
    2. host/decks/{deck_id}
        - Retorna os dados do deck com o id passado

# Cards

1. Post
    1. host/cards
        - Cadastra uma nova carta
        - Body
        
        ```json
        {
            "cardName": "Dragão branco de olhos azúis",
            "edition": "1.0",
            "language": "br",
            "foil": false,
            "value": 100.00
        }
        ```
        
2. Put
    1. host/cards/{card_id}
        - Atualiza os valores de uma carta
        - Body
        
        ```json
        {
            "cardName": "Dragão branco de olhos azúis",
            "edition": "2.0",
            "language": "jp",
            "foil": false,
            "value": 200.00
        }
        ```
        
3. Delete
    1. host/cards/{card_id}
        - Apaga uma carta
4. Get
    1. host/cards
        - Retorna todas as cartas
    2. host/cards/{card_id}
        - Retorna a carta com id específico
