package com.developerjp.JieunMotivationalQ;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;


public class ShowQuotes extends AppCompatActivity {

    private final ArrayList<String> quotesList = new ArrayList<>(); // Initialize the list here

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Grabs the value of the person clicked
        Intent intent = getIntent();
        String person = intent.getStringExtra("person");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the text to the name of the person
        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(person);

        // Populate the array with data - the quotes to show
        showQuotes(person);

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapterQuotes adapter = new MyRecyclerViewAdapterQuotes(this, quotesList);
        recyclerView.setAdapter(adapter);

        // Generate a random index and set it for the adapter
        Random random = new Random();
        int randomIndex = quotesList.isEmpty() ? 0 : random.nextInt(quotesList.size());
        adapter.setSelectedQuoteIndex(randomIndex);
    }

    private void showQuotes(String person) {
        quotesList.clear(); // Clear the list before adding new quotes

        //switch statement to show the correct quotes depending on which person was selected
        switch (person) {
            case "Woman Power":
                quotesList.add("The question isn’t who’s going to let me; it’s who’s going to stop me. - Ayn Rand");
                quotesList.add("I can't think of any better representation of beauty than someone who is unafraid to be herself. - Emma Stone");
                quotesList.add("The most courageous act is still to think for yourself. Aloud. - Coco Chanel");
                quotesList.add("The most effective way to do it is to do it. - Amelia Earhart");
                quotesList.add("I want every girl to know that her voice can change the world. - Malala Yousafzai");
                quotesList.add("We realize the importance of our voices only when we are silenced. - Malala Yousafzai");
                quotesList.add("The best protection any woman can have is courage. - Elizabeth Cady Stanton");
                quotesList.add("Step out of the history that is holding you back. Step into the new story you are willing to create. - Oprah Winfrey");
                quotesList.add("I’m tough, ambitious, and I know exactly what I want. If that makes me a bitch, okay. - Madonna");
                quotesList.add("The most difficult thing is the decision to act, the rest is merely tenacity. - Amelia Earhart");
                quotesList.add("You are more powerful than you know; you are beautiful just as you are. - Melissa Etheridge");
                quotesList.add("One cannot accomplish anything without fanaticism. - Eva Perón");
                quotesList.add("Success is not final, failure is not fatal: It is the courage to continue that counts. - Sojourner Truth");
                quotesList.add("Buckle up, and know that it’s going to be a tremendous amount of work, but embrace it. - Tory Burch");
                quotesList.add("I’m not afraid of storms, for I’m learning to sail my ship. - Louisa May Alcott");
                quotesList.add("I'd rather regret the things I've done than regret the things I haven't done. - Lucille Ball");
                quotesList.add("You may not control all the events that happen to you, but you can decide not to be reduced by them. - Maya Angelou");
                quotesList.add("The best way to predict the future is to create it. - Dr. Mae Jemison");
                quotesList.add("You are the one that possesses the keys to your being. You carry the passport to your own happiness. - Diane von Fürstenberg");
                quotesList.add("I am my best work - a series of road maps, reports, recipes, doodles, and prayers from the front lines. - Audre Lorde");
                quotesList.add("You are your best thing. - Toni Morrison");
                quotesList.add("Don't wait around for other people to be happy for you. Any happiness you get you've got to make yourself. - Alice Walker");
                quotesList.add("I don't know what the future holds, but I do know that I'm going to be positive and not wake up feeling desperate. - Billie Eilish");
                quotesList.add("I never dreamed about success. I worked for it. - Estée Lauder");
                quotesList.add("The most common way people give up their power is by thinking they don't have any. - Alice Walker");
                quotesList.add("No one can make you feel inferior without your consent. - Eleanor Roosevelt");
                quotesList.add("If you're not making mistakes, then you're not making decisions. - Catherine Cook");
                quotesList.add("Do not wait for leaders; do it alone, person to person. - Mother Teresa");
                quotesList.add("I am not a product of my circumstances. I am a product of my decisions.");
                quotesList.add("You may be the only person left who believes in you, but it's enough. It takes just one star to pierce a universe of darkness. Never give up.");
                quotesList.add("I raise up my voice—not so I can shout, but so that those without a voice can be heard...we cannot succeed when half of us are held back. - Malala Yousafzai");
                quotesList.add("I work really hard to just focus on the joy of the work that gets to be done and the impact of that rather than the ego of it. - Ava DuVernay");
                quotesList.add("The difference between a broken community and a thriving one is the presence of women who are valued. - Michelle Obama");
                break;

            case "Through Storm":
                quotesList.add("Tough times never last, but tough people do. - Robert H. Schuller");
                quotesList.add("This too shall pass.");
                quotesList.add("You are stronger than you think.");
                quotesList.add("Every storm runs out of rain.");
                quotesList.add("The darkest hour has only sixty minutes.");
                quotesList.add("Believe you can and you're halfway there. - Theodore Roosevelt");
                quotesList.add("In the middle of difficulty lies opportunity. - Albert Einstein");
                quotesList.add("Stars can't shine without darkness.");
                quotesList.add("Your present circumstances don't determine where you can go, they merely determine where you start. - Nido Qubein");
                quotesList.add("When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. - Henry Ford");
                quotesList.add("You are not a product of your circumstances; you are a product of your decisions.");
                quotesList.add("Don't give up. Normally it is the last key on the ring that opens the door. - Paulo Coelho");
                quotesList.add("It's okay not to be okay as long as you are not giving up.");
                quotesList.add("Difficulties in life are intended to make us better, not bitter.");
                quotesList.add("Even the darkest night will end, and the sun will rise. - Victor Hugo");
                quotesList.add("The strongest people are not those who show strength in front of us but those who win battles we know nothing about.");
                quotesList.add("When you come out of the storm, you won’t be the same person who walked in. That’s what the storm is all about.");
                quotesList.add("Sometimes you have to go through the worst to get to the best.");
                quotesList.add("Keep your head high, keep your chin up, and most importantly, keep smiling, because life's a beautiful thing and there's so much to smile about. - Marilyn Monroe");
                quotesList.add("You were given this life because you are strong enough to live it.");
                quotesList.add("You are not alone in this journey. Others have faced similar challenges and emerged stronger.");
                quotesList.add("Challenges are what make life interesting. Overcoming them is what makes life meaningful.");
                quotesList.add("Strength does not come from the body. It comes from the will.");
                quotesList.add("The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt");
                quotesList.add("Every day may not be good, but there is something good in every day.");
                quotesList.add("You have within you right now, everything you need to deal with whatever the world can throw at you.");
                quotesList.add("Difficulties strengthen the mind, as labor does the body. - Seneca");
                quotesList.add("The sun himself is weak when he first rises, and gathers strength and courage as the day gets on. - Charles Dickens");
                quotesList.add("You are not defined by your tough times but how you choose to handle them.");
                quotesList.add("Don't count the days, make the days count.");
                quotesList.add("No matter how much it hurts now, someday you will look back and realize your struggles changed your life for the better.");
                quotesList.add("You are braver than you believe, stronger than you seem, and smarter than you think. - A.A. Milne");
                quotesList.add("Life is 10% what happens to us and 90% how we react to it.");
                quotesList.add("It always seems impossible until it's done. - Nelson Mandela");
                break;

            case "Confucius":
                quotesList.add("Our greatest glory is not in never falling, but in rising every time we fall.");
                quotesList.add("Real knowledge is to know the extent of one’s ignorance.");
                quotesList.add("It does not matter how slowly you go as long as you do not stop.");
                quotesList.add("Ignorance is the night of the mind, but a night without moon and star.");
                quotesList.add("When we see men of a contrary character, we should turn inwards and examine ourselves.");
                quotesList.add("Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.");
                quotesList.add("To see what is right and not do it is the want of courage.");
                quotesList.add("If you make a mistake and do not correct it, this is called a mistake.");
                quotesList.add("The superior man is modest in his speech but exceeds in his actions.");
                quotesList.add("He who learns but does not think, is lost! He who thinks but does not learn is in great danger.");
                quotesList.add("To be wronged is nothing, unless you continue to remember it.");
                quotesList.add("When anger rises, think of the consequences.");
                quotesList.add("Respect yourself and others will respect you.");
                quotesList.add("Real knowledge is to know the extent of one's ignorance.");
                quotesList.add("To see what is right and not do it is want of courage.");
                quotesList.add("It is not the employer who pays the wages. Employers only handle the money. It is the customer who pays the wages.");
                quotesList.add("Wheresoever you go, go with all your heart.");
                quotesList.add("The man who asks a question is a fool for a minute, the man who does not ask is a fool for life.");
                quotesList.add("It is not the failure of others to appreciate your abilities that should trouble you, but rather your failure to appreciate theirs.");
                quotesList.add("The superior man understands what is right; the inferior man understands what will sell.");
                quotesList.add("The man who moves a mountain begins by carrying away small stones.");
                quotesList.add("When you know a thing, to hold that you know it; and when you do not know a thing, to allow that you do not know it - this is knowledge.");
                quotesList.add("To be wronged is nothing unless you continue to remember it.");
                quotesList.add("Life is really simple, but we insist on making it complicated.");
                quotesList.add("The will to win, the desire to succeed, the urge to reach your full potential... these are the keys that will unlock the door to personal excellence.");
                quotesList.add("Choose a job you love, and you will never have to work a day in your life.");
                quotesList.add("When it is obvious that the goals cannot be reached, don't adjust the goals, adjust the action steps.");
                quotesList.add("It does not matter how slowly you go, as long as you do not stop.");
                quotesList.add("Wheresoever you go, go with all your heart.");
                quotesList.add("Learning without thought is labor lost; thought without learning is perilous.");
                quotesList.add("To go beyond is as wrong as to fall short.");
                quotesList.add("He who learns but does not think, is lost! He who thinks but does not learn is in great danger.");
                quotesList.add("To be wronged is nothing, unless you continue to remember it.");
                quotesList.add("Life is really simple, but we insist on making it complicated.");
                break;

            case "Believe it!":
                quotesList.add("It’s easy to dream, but much harder to execute it.");
                quotesList.add("Skills are cheap. Passion is priceless.");
                quotesList.add("Look yourself in the mirror and ask yourself, what do I want to do every day for the rest of my life…Do that.");
                quotesList.add("If you live for the weekends and vacations, your life is broken.");
                quotesList.add("Without hustle, talent will only carry you so far.");
                quotesList.add("There’s no reason to do things you hate. None.");
                quotesList.add("Ideas are nothing. Execution is the game.");
                quotesList.add("We cannot solve our problems with the same thinking we used to create them.");
                quotesList.add("Imagination is everything. It is the preview of life’s coming attractions.");
                quotesList.add("Learn from yesterday, live for today, hope for tomorrow. The important thing is not to stop questioning.");
                quotesList.add("Anyone who has never made a mistake has never tried anything new.");
                quotesList.add("The difference between genius and stupidity is that genius has its limits.");
                quotesList.add("Insanity is doing the same thing over and over again and expecting different results.");
                quotesList.add("If you can’t explain it simply, you don’t understand it well enough.");
                quotesList.add("As a human being, one has been endowed with just enough intelligence to be able to see clearly how utterly inadequate that intelligence is when confronted with what exists.");
                quotesList.add("Life is like riding a bicycle. To keep your balance you must keep moving.");
                quotesList.add("It’s not that I’m so smart, it’s just that I stay with problems longer.");
                quotesList.add("In order to form an immaculate member of a flock of sheep one must, above all, be a sheep.");
                quotesList.add("Only those who attempt the absurd can achieve the impossible.");
                quotesList.add("A man should look for what is, and not for what he thinks should be.");
                quotesList.add("In the middle of difficulty lies opportunity.");
                quotesList.add("A human being is part of a whole called by us the universe.");
                quotesList.add("A happy man is too satisfied with the present to dwell too much on the future.");
                quotesList.add("A ship is always safe at the shore, but that is not what it is built for.");
                quotesList.add("If you want to live a happy life, tie it to a goal, not to people or things.");
                quotesList.add("Logic will get you from A to Z. Imagination will get you everywhere.");
                quotesList.add("Common sense is what tells us the earth is flat.");
                quotesList.add("Believe you can and you're halfway there.");
                quotesList.add("Success is stumbling from failure to failure with no loss of enthusiasm.");
                quotesList.add("The only way to achieve the impossible is to believe it is possible.");
                quotesList.add("You are never too old to set another goal or to dream a new dream.");
                quotesList.add("The future belongs to those who believe in the beauty of their dreams.");
                quotesList.add("Your time is limited, don't waste it living someone else's life.");
                quotesList.add("It's not whether you get knocked down, it's whether you get up.");
                quotesList.add("The only place where success comes before work is in the dictionary.");
                quotesList.add("Success usually comes to those who are too busy to be looking for it.");
                quotesList.add("Don't watch the clock; do what it does. Keep going.");
                quotesList.add("I’d rather be an optimist and a fool than a pessimist and right.");
                quotesList.add("The one who follows the crowd will usually go no further than the crowd. Those who walk alone are likely to find themselves in places no one has ever been before.");
                quotesList.add("What is right is not always popular and what is popular is not always right.");
                quotesList.add("I speak to everyone in the same way, whether he is the garbage man or the president of the university.");
                quotesList.add("If you don’t find the time, if you don’t do the work, you don’t get the results.");
                quotesList.add("Be hungry for success, hungry to make your mark, hungry to be seen and to be heard and to have an effect. And as you move up and become successful, make sure also to be hungry for helping others.");
                quotesList.add("Just remember, you can’t climb the ladder of success with your hands in your pockets.");
                quotesList.add("While you’re out there partying, horsing around, someone out there at the same time is working hard. Someone is getting smarter and someone is winning. Just remember that.");
                quotesList.add("Don’t listen to the naysayers. Make sure that you’re working your butt off. Make sure that you have a very clear vision of where you want to go. Don’t shoot for lower goals – shoot for the stars.");
                quotesList.add("If you don’t believe in yourself, then how will anyone else believe in you?");
                break;

            case "Feel it!":
                quotesList.add("Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill");
                quotesList.add("Believe you can and you're halfway there. - Theodore Roosevelt");
                quotesList.add("The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt");
                quotesList.add("Don't watch the clock; do what it does. Keep going. - Sam Levenson");
                quotesList.add("The only way to do great work is to love what you do. - Steve Jobs");
                quotesList.add("Success is walking from failure to failure with no loss of enthusiasm. - Winston S. Churchill");
                quotesList.add("The harder you work for something, the greater you'll feel when you achieve it. - Unknown");
                quotesList.add("Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful. - Albert Schweitzer");
                quotesList.add("Your time is limited, don't waste it living someone else's life. - Steve Jobs");
                quotesList.add("Challenges are what make life interesting and overcoming them is what makes life meaningful. - Joshua J. Marine");
                quotesList.add("The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt");
                quotesList.add("Strive not to be a success, but rather to be of value. - Albert Einstein");
                quotesList.add("Success is not in what you have, but who you are. - Bo Bennett");
                quotesList.add("The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson");
                quotesList.add("The secret of getting ahead is getting started. - Mark Twain");
                quotesList.add("The road to success and the road to failure are almost exactly the same. - Colin R. Davis");
                quotesList.add("Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle. - Christian D. Larson");
                quotesList.add("Don't be pushed around by the fears in your mind. Be led by the dreams in your heart. - Roy T. Bennett");
                quotesList.add("What you get by achieving your goals is not as important as what you become by achieving your goals. - Zig Ziglar");
                quotesList.add("You are never too old to set another goal or to dream a new dream. - C.S. Lewis");
                quotesList.add("The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh");
                quotesList.add("Don't count the days, make the days count. - Muhammad Ali");
                quotesList.add("Believe in yourself, take on your challenges, dig deep within yourself to conquer fears. - Chantal Sutherland");
                quotesList.add("You have to expect things of yourself before you can do them. - Michael Jordan");
                quotesList.add("Success is liking yourself, liking what you do, and liking how you do it. - Maya Angelou");
                quotesList.add("Don't be afraid to give up the good to go for the great. - John D. Rockefeller");
                quotesList.add("The journey of a thousand miles begins with one step. - Lao Tzu");
                quotesList.add("The only thing standing between you and your dream is the will to try and the belief that it is actually possible. - Joel Brown");
                quotesList.add("Hardships often prepare ordinary people for an extraordinary destiny. - C.S. Lewis");
                quotesList.add("You must do the things you think you cannot do. - Eleanor Roosevelt");
                quotesList.add("Keep your face always toward the sunshine—and shadows will fall behind you. - Walt Whitman");
                quotesList.add("I can't change the direction of the wind, but I can adjust my sails to always reach my destination. - Jimmy Dean");
                quotesList.add("Opportunities don't happen. You create them. - Chris Grosser");
                quotesList.add("Your time is limited, so don't waste it living someone else's life. - Steve Jobs");
                quotesList.add("What lies behind us and what lies before us are tiny matters compared to what lies within us. - Ralph Waldo Emerson");
                quotesList.add("A person who never made a mistake never tried anything new. - Albert Einstein");
                quotesList.add("If you can dream it, you can do it. - Walt Disney");
                quotesList.add("Our greatest glory is not in never falling, but in rising every time we fall. - Confucius");
                quotesList.add("Success is not the key to happiness. Happiness is the key to success. - Albert Schweitzer");
                break;
        }
    }
}