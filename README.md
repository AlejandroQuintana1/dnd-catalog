# DnD Catalog API

A comprehensive **Spring Boot RESTful API** for managing Dungeons & Dragons (5th Edition) character data, including characters, spells, items, skills, campaigns, quests, and encounters.

## 📋 Overview

DnD Catalog is a backend service designed to support D&D 5e gameplay by providing robust data management for:
- **Characters**: Player characters, NPCs, and creatures with full stat management
- **Skills**: Proficiency tracking with automatic bonus calculations
- **Spells**: Spell catalog with school, level, and character grimoire management
- **Items**: Equipment, weapons, armor with slot-based inventory system
- **Campaigns & Quests**: Campaign organization and quest tracking
- **Encounters**: Combat encounter management with initiative tracking
- **Synchronization**: Multi-device sync support for mobile/desktop apps

## 🏗️ Architecture

### Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 3.5.8-SNAPSHOT |
| **Java** | OpenJDK | 21 |
| **Database** | PostgreSQL | Latest |
| **ORM** | Hibernate/JPA | (via Spring Data JPA) |
| **Build Tool** | Maven | 3.x |
| **API Documentation** | Springdoc OpenAPI (Swagger UI) | 2.1.0 |
| **Utilities** | Lombok | Latest |

### Project Structure

```
src/main/java/com/aqe/dnd/dnd_catalog/
├── DndCatalogApplication.java       # Main Spring Boot application
├── config/
│   └── OpenApiConfig.java           # OpenAPI/Swagger configuration
└── features/                        # Feature-based modular architecture
    ├── character/                   # Character management
    │   ├── controller/              # REST controllers
    │   ├── domain/                  # JPA entities
    │   ├── dto/                     # Data Transfer Objects
    │   ├── repository/              # Spring Data JPA repositories
    │   └── service/                 # Business logic
    ├── item/                        # Item & inventory management
    ├── skill/                       # Skills & proficiency management
    └── spell/                       # Spell catalog management
```

### Database Schema

The application uses a **PostgreSQL database** with the `dnd` schema. The database design includes:

#### Core Custom Domains
- `stat`: Ability scores (1-30)
- `level`: Character levels (1-20)
- `bonus`: Modifiers (-99 to 99)
- `spell_level`: Spell levels (0-9 cantrips to 9th level)
- `stat_code`: Ability codes (STR, DEX, CON, INT, WIS, CHA)
- `nonempty_text`: Non-empty string validation

#### Main Tables & Features

**Characters (`dnd.characters`)**
- UUID-based primary keys
- Automatic proficiency bonus calculation based on level
- Soft delete support (`deleted_at`)
- Links to stats, skills, items, spells, party, and campaign
- Character types: Player, NPC, Monster

**Stats (`dnd.stats`)**
- Six ability scores with auto-calculated modifiers
- Generated columns for ability modifiers using D&D 5e formula: `floor((score - 10) / 2)`

**Skills (`dnd.skill`, `dnd.character_skill`)**
- 18 standard D&D skills (Acrobatics, Athletics, etc.)
- 6 ability check skills (Strength, Dexterity, etc.)
- Additional computed skills (AC, Initiative, Speed, HitPoints)
- Proficiency tracking per character
- View `v_character_skill` for auto-calculated skill bonuses

**Saving Throws (`dnd.saving_throw`, `dnd.character_saving_throw`)**
- Six saving throws (one per ability)
- Proficiency tracking
- View `v_character_saving_throw` for total bonuses

**Items (`dnd.item`, `dnd.item_slot`, `dnd.character_item`)**
- Slot-based equipment system (head, chest, hands, mainHand, offHand, twoHanded, etc.)
- Item skill bonuses (`dnd.item_skill_bonus`)
- Attunement tracking
- Inventory management with quantity and equipped status
- Unique constraint per slot when equipped
- View `v_character_inventory` for character inventory with weight calculations

**Spells (`dnd.spell`, `dnd.spell_school`, `dnd.character_spell`)**
- Eight spell schools (Abjuration, Conjuration, Divination, etc.)
- Levels 0-9 (cantrips to 9th level spells)
- Spell components, casting time, range, duration
- Preparation tracking per character
- View `v_character_spells` for character grimoires

**Campaigns, Quests & Parties**
- Campaign management (`dnd.campaign`)
- Quest tracking with status (open, in_progress, completed, failed)
- Party system linking characters to campaigns

**Encounters (`dnd.encounter`, `dnd.encounter_member`)**
- Combat encounter management
- Initiative tracking for characters and creatures
- HP tracking during encounters
- View `v_encounter_order` for initiative-sorted participant lists

**Creatures (`dnd.creature`)**
- Bestiary/monster catalog
- Challenge Rating (CR), HP, AC
- Can participate in encounters

**Damage Types (`dnd.damage_type`, `dnd.character_damage_trait`, `dnd.creature_damage_trait`)**
- Damage type catalog
- Resistance, immunity, vulnerability tracking

**Synchronization (`dnd.sync_device`, `dnd.sync_checkpoint`)**
- Multi-device sync support
- Per-table checkpoint tracking
- Device registration and last-seen tracking

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.x**
- **PostgreSQL** database server
- Git (for version control)

### Database Setup

1. **Create PostgreSQL database:**
   ```bash
   createdb dnd_db
   ```

2. **Run the DDL scripts** from [`doc/create_db.md`](doc/create_db.md):
   ```sql
   -- Connect to your database
   psql -d dnd_db

   -- Execute the DDL statements from create_db.md
   -- This includes schema creation, domains, tables, views, and initial data
   ```

3. **Configure database connection** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/dnd_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

   Or use environment variables:
   ```bash
   export DB_HOST=localhost
   export DB_PORT=5432
   export POSTGRES_DB=dnd_db
   export POSTGRES_USER=your_username
   export POSTGRES_PASSWORD=your_password
   ```

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd dnd-catalog
   ```

2. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API:**
   - **Base URL:** `http://localhost:8081/api/v1`
   - **Swagger UI:** `http://localhost:8081/api/v1/swagger-ui.html`
   - **OpenAPI JSON:** `http://localhost:8081/api/v1/v3/api-docs`

## 📚 API Documentation

The API is fully documented using **OpenAPI 3.0** (Swagger). Once the application is running, visit:

**Swagger UI:** [http://localhost:8081/api/v1/swagger-ui.html](http://localhost:8081/api/v1/swagger-ui.html)

### Available Endpoints

The API provides RESTful endpoints for:

#### Character Management
- `GET /characters` - List all characters
- `POST /characters` - Create a new character
- `GET /characters/{id}` - Get character by ID
- `PUT /characters/{id}` - Update character
- `DELETE /characters/{id}` - Soft delete character

#### Spell Management
- `GET /spells` - List all spells
- `POST /spells` - Create a new spell
- `GET /spells/{id}` - Get spell by ID
- `PUT /spells/{id}` - Update spell
- `DELETE /spells/{id}` - Delete spell

#### Item Management
- `GET /items` - List all items
- `POST /items` - Create a new item
- `GET /items/{id}` - Get item by ID with skill bonuses
- `PUT /items/{id}` - Update item
- `DELETE /items/{id}` - Delete item

#### Skill Management
- `GET /skills` - List all skills
- `GET /skills/{id}` - Get skill by ID
- `POST /character-skills` - Assign skill proficiency to character
- `GET /character-skills/{characterId}` - Get character's skill proficiencies

> **Note:** Refer to the Swagger UI for complete endpoint documentation, request/response schemas, and interactive testing.

## ⚙️ Configuration

### Application Properties

Key configuration settings in `application.properties`:

```properties
# Application
spring.application.name=dnd-catalog
server.port=8081
server.servlet.context-path=/api/v1

# Database
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${POSTGRES_DB:dnd_db}
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.default_schema=dnd

# OpenAPI/Swagger
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `POSTGRES_DB` | Database name | `dnd_db` |
| `POSTGRES_USER` | Database username | `chismito` |
| `POSTGRES_PASSWORD` | Database password | (sensitive) |

## 🧪 Testing

Run unit and integration tests:

```bash
./mvnw test
```

## 📁 Key Files & Documentation

- **[doc/create_db.md](doc/create_db.md)** - Complete database DDL with table definitions, domains, views, and sample data
- **[pom.xml](pom.xml)** - Maven dependencies and build configuration
- **[application.properties](src/main/resources/application.properties)** - Application configuration

## 🎯 Features Implemented

- ✅ **Character Management** - Full CRUD operations with stats, skills, and proficiency tracking
- ✅ **Skill System** - 18 D&D skills + ability checks with automatic bonus calculations
- ✅ **Spell Catalog** - Spell management with schools, levels, and character grimoires
- ✅ **Item System** - Equipment, weapons, armor with slot-based inventory and skill bonuses
- ✅ **Campaign & Quest Tracking** - Organize gameplay sessions and storylines
- ✅ **Encounter Management** - Combat tracking with initiative and HP
- ✅ **Soft Delete** - Entity preservation with `deleted_at` timestamps
- ✅ **OpenAPI Documentation** - Interactive API documentation via Swagger UI
- ✅ **Multi-device Sync** - Sync checkpoint system for mobile/desktop clients

## 🔮 Future Enhancements

- [ ] **Session Management** - Real-time game session support with event logs
- [ ] **Authentication & Authorization** - User management and role-based access control
- [ ] **WebSocket Support** - Real-time updates for collaborative gameplay
- [ ] **Character Import/Export** - JSON/XML character sheet import/export
- [ ] **Dice Roller API** - Built-in dice rolling service
- [ ] **Homebrew Content** - Custom classes, races, spells, and items

## 📜 License

*(Specify your license here, e.g., MIT, Apache 2.0, etc.)*

## 👥 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📞 Contact

For questions or support, please contact the development team.

---

**Happy Adventuring! 🎲⚔️🐉**
