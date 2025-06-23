
# MotiMosaic

## Overview

**MotiMosaic** is a motivational quotes app targeting women in their 20s and 30s. It features a modern Material 3 design, a fresh color palette, and a user-friendly interface. The app displays daily inspiration, categorized quote lists, and supports notifications.

---

## Project Structure

```
app/
  ├── build.gradle
  ├── src/
  │   ├── main/
  │   │   ├── java/com/developerjp/JieunMotivationalQ/
  │   │   │   ├── MainActivity.java
  │   │   │   ├── ShowQuotes.java
  │   │   │   ├── MyRecyclerViewAdapterPeople.java
  │   │   │   ├── MyRecyclerViewAdapterQuotes.java
  │   │   │   ├── Notification.java
  │   │   │   ├── NotificationScheduler.java
  │   │   ├── res/
  │   │   │   ├── layout/
  │   │   │   │   ├── activity_main.xml
  │   │   │   │   ├── activity_show_quotes.xml
  │   │   │   │   ├── cardview_person.xml
  │   │   │   │   ├── cardview_quotes.xml
  │   │   │   │   ├── adview_layout.xml
  │   │   │   ├── values/
  │   │   │   │   ├── colors.xml
  │   │   │   │   ├── strings.xml
  │   │   │   │   ├── themes.xml
  │   │   │   │   ├── themes.xml (night)
  │   │   │   ├── drawable/
  │   │   │   │   ├── ic_chevron_right.xml
  │   │   │   │   ├── baseline_notifications_24.xml
  │   │   │   │   └── ... (other images)
  │   │   │   ├── menu/
  │   │   │   │   └── main_menu.xml
  │   │   │   └── ... (other resources)
  └── ...
```

---

## Theming & Colors

- **Material 3 Theme:**  
  The app uses `Theme.Material3.DayNight.NoActionBar` for a modern look and dark mode support.
- **Color Palette:**  
  - Primary: Rose (`#D3A5A5`)
  - Primary Dark: Deeper Rose (`#C18E8E`)
  - Accent: Soft Gold (`#E1C4A9`)
  - Text: Dark Gray (`#333333`)
  - Background: Off-white (`#FBFBFB`)
- **Customization:**  
  All colors are defined in `res/values/colors.xml` and referenced via theme attributes in layouts and styles.

---

## UI Architecture

### Main Screen (`MainActivity` / `activity_main.xml`)

- **MaterialToolbar:**  
  - Displays app name and a notification icon (menu).
- **Daily Quote Card:**  
  - Prominently displays a daily inspirational quote.
- **RecyclerView:**  
  - Shows categories of quotes (e.g., “Success in Action”, “Woman Power”).
  - Uses `MyRecyclerViewAdapterPeople` and `cardview_person.xml` for each item.

### Quotes Screen (`ShowQuotes` / `activity_show_quotes.xml`)

- **MaterialToolbar:**  
  - Shows the selected category as the title and a back button.
- **RecyclerView:**  
  - Displays a shuffled list of quotes for the selected category.
  - Uses `MyRecyclerViewAdapterQuotes` and `cardview_quotes.xml` for each quote.
  - Interleaves AdMob banners every 8 quotes (see `adview_layout.xml`).

---

## Key Classes

### `MainActivity.java`
- Sets up the main UI and RecyclerView.
- Handles notification permission and scheduling via the toolbar menu.
- Launches `ShowQuotes` when a category is selected.

### `ShowQuotes.java`
- Receives the selected category via intent.
- Sets up the toolbar and RecyclerView for quotes.
- Populates and shuffles quotes for the selected category.

### `MyRecyclerViewAdapterPeople.java`
- Adapter for the main category list.
- Binds category name and image to each card.

### `MyRecyclerViewAdapterQuotes.java`
- Adapter for the quotes list.
- Binds quote text and author to each card.
- Interleaves AdMob banners.

---

## Layouts

- **activity_main.xml:**  
  LinearLayout with a MaterialToolbar, a CardView for the daily quote, and a RecyclerView for categories.
- **activity_show_quotes.xml:**  
  LinearLayout with a MaterialToolbar and a RecyclerView for quotes.
- **cardview_person.xml:**  
  MaterialCardView with a circular image, category name, and a chevron icon.
- **cardview_quotes.xml:**  
  MaterialCardView with two TextViews: one for the quote, one for the author.
- **adview_layout.xml:**  
  LinearLayout containing an AdMob `AdView`.

---

## Resources

- **Strings:**  
  All user-facing text is in `res/values/strings.xml`.
- **Icons:**  
  Vector drawables (e.g., `ic_chevron_right.xml`, `baseline_notifications_24.xml`) are used for crisp, scalable icons.
- **Menus:**  
  Toolbar menu is defined in `res/menu/main_menu.xml`.

---

## Notifications

- **Permission Handling:**  
  Uses Android 13+ notification permission APIs.
- **Scheduling:**  
  `NotificationScheduler` handles scheduling and showing notifications.

---

## Ads

- **AdMob Integration:**  
  Banner ads are shown every 8 quotes in the quotes list.
- **AdView:**  
  Defined in `adview_layout.xml` and managed by `MyRecyclerViewAdapterQuotes`.

---

## Extending the App

- **Adding Categories/Quotes:**  
  Update the `showQuotes` method in `ShowQuotes.java` to add new categories and their quotes.
- **Changing the Daily Quote:**  
  Update the string resource or implement logic in `MainActivity` to fetch a new quote each day.
- **Theming:**  
  Adjust colors in `colors.xml` and update `themes.xml` as needed.
- **Adding Features:**  
  Use Material 3 components for consistency and extend layouts/adapters as needed.

---

## Best Practices

- Use theme attributes for all colors and text appearances.
- Keep all user-facing strings in `strings.xml`.
- Use vector drawables for icons.
- Keep layouts flat and use Material components for modern UI/UX.
- Keep adapters and activities clean and focused on their responsibilities.

---

## Contact

For questions or contributions, please contact the original developer or open an issue in the project repository.

---
