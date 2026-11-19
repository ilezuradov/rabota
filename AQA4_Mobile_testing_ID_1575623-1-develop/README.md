# Mobile testing (AQA)

In this project you’ll learn how to write automated tests for Android apps using Espresso. You’ll get hands on experience interacting with UI elements, writing stable tests, configuring launch parameters, and working with emulators — all using the framework's built-in tools.

💡 [Press here](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624) **to leave feedback on this project.** It's anonymous and will help our «School 21» team improve the learning experience. We recommend completing the survey immediately after finishing the project.

## Contents

  - [Chapter 1](#chapter-1)
  - [1.1. General instructions](#11-general-instructions)
  - [Chapter 2](#chapter-2)
    - [2.1. Introduction](#21-introduction)
  - [Chapter 3](#chapter-3)
    - [3.1. About Mobile testing](#31-about-mobile-testing)
    - [3.2. Appium](#32-appium)
    - [3.3. Appium Inspector](#33-appium-inspector)
    - [3.4. Espresso](#34-espresso)
  - [Chapter 4](#chapter-4)
    - [4.1. Getting Started with the Framework](#41-getting-started-with-the-framework)
    - [Task 1. Working with Espresso](#task-1-working-with-espresso)

## Chapter 1

## 1.1. General instructions

How to learn at “School 21”:

- Here, you’ll find a unique learning experience with a lot of freedom. You’re given a task and left to find your own way to solve it, using whatever resources work best for you — whether that’s the Internet or AI tools like GigaChat. Just be mindful of information quality: verify, think critically, analyze, and compare.
- Peer-to-peer (P2P) learning is the exchange of knowledge and experience with peers, where everyone acts as both mentor and student. This approach allows you to gain a deeper understanding of the material by learning from one another.
- Feel free to ask for help: around you are peers who are also navigating this path for the first time. Share your own experience and ideas with others.  Join Rocket.Chat to stay updated with the latest community announcements. 
- Your learning is meaningless if you just copy someone else’s solutions. When receiving help from others, always make sure you fully understand the “why”, “how”, and “purpose” behind the solution. Don’t be afraid to make mistakes. 
- Does the task seem impossible? Take a break, get some fresh air and clear your mind — this has helped many people. Maybe after that, the solution will come to you naturally.
- The learning process is just as important as the result. It’s not just about completing the task — it’s about understanding HOW to solve it. 

How to work with the project:

- Before starting, clone the project from GitLab into a repository with the same name.
- All files should be created inside the _src/_ folder of the cloned repository.
- After cloning the project, create a _develop_ branch and do all your development there. Then, push the _develop_ branch to GitLab.
- Your directory should not contain any files other than those specified in the assignments.

## Chapter 2

### 2.1. Introduction

Throughout previous projects, you have built a solid foundation in testing by working with JUnit, TestNG, REST-Assured, OkHttp, Retrofit, Selenide, and Cucumber. You have used record and data classes to parse data and Allure to generate reports.

In this final project on mobile testing, you will expand your toolkit as an AQA engineer by learning several more in-demand technologies that recruiters often seek.

Mobile application testing is the next logical step after mastering basic automation skills, such as API testing.

You will encounter unique challenges, such as diverse devices, various OS versions, gestures, and specific interaction scenarios, that are common in mobile testing.

This project focuses on the two most widely used tools in enterprise development:

- Appium, the standard for cross-platform automation (iOS and Android);
- Espresso, a framework for writing Android UI tests alongside your application code.

You will gain hands-on experience writing automated tests for an Android app using Espresso.

This is the most comprehensive and demanding project yet, as it brings together skills and concepts from your previous work. Be prepared to actively apply knowledge from previous projects.

Let's go!

## Chapter 3

### 3.1. About Mobile testing

_What do you think makes mobile automation different from web automation?_

Take a moment to think about it: what comes to mind first?

We’ll come back to this question, but for now...

In the first part of the QA-programme (manual testing) of the QA08_Mobile Testing project, you explored the unique aspects of mobile testing and discussed how mobile apps are much more than just "miniature websites".

Mobile applications operate in their own ecosystem, which brings several key differences:

- Device diversity: different screen sizes, resolutions, and iOS/Android versions.
- Interaction specifics: gestures like swipes, taps, and zooms; screen orientation changes; and interruptions such as calls and notifications.
- Application types: native apps (built specifically for an OS), hybrid apps (using web views), and progressive web apps (PWAs).

Now, about the **differences between testing mobile and web applications**:

| **Tools** | **Emulation vs. Real Devices** | **Stability Challenges**
| :- | :- | :- |
| iOS and Android often require different testing approaches (for example, XCUITest for iOS and Espresso for Android), but tools like Appium provide a unified API to handle both platforms. | Tests may behave differently on simulators and real hardware. | Reproducibility can be affected by factors such as CPU load, memory usage, and background processes. |

### 3.2. Appium

Appium is an open-source framework for automating mobile applications. Although it follows the "write once, run anywhere" principle, there are still some platform-specific differences to keep in mind.

Supports:

- iOS (native, hybrid, and mobile web applications);
- Android (native, hybrid, and web);
- Windows (for desktop applications, though this is less common).

**How does Appium work behind the scenes?**

Appium uses a client-server architecture.

- You write your test scripts in a language such as Java or Python. These scripts communicate with the Appium server using the JSON Wire Protocol.
- The server then translates these commands into platform-specific actions using XCUITest for iOS or UiAutomator2/Espresso for Android, allowing your tests to interact with the app as a real user would.

**Key components:**

- Appium Server: A bridge between your test code and the mobile OS.
- Drivers, such as XCUITest and UiAutomator2, handle the actual interaction with the app’s UI elements.
- Sessions: Each session represents a unique combination of an application under test and a specific device.

**Why do AQA engineers choose Appium?**

Cross-platform testing: you can use a single codebase to automate tests for both iOS and Android apps-as long as the apps share the same functionality.

- Real devices and emulators: Run your tests on an iPhone simulator and an actual Samsung device simultaneously.
- Language flexibility: Write your test scripts in Java, Python, JavaScript, C#, and other languages.
- CI/CD integration: Appium integrates smoothly with Jenkins, GitLab CI, and similar continuous integration tools.

**Typical testing scenarios with Appium**:

| Testing native applications | Hybrid applications: | End-to-end scenarios: |
| :- | :- | :- |
| - Authentication checks; <br/>- Test interactions with the camera, geolocation, and notifications;<br/>- Validate touch actions like swiping and long pressing.| - Switching between the native app and WebView;<br/>- Testing embedded web pages. | - Interacting with other apps (for example, switching from a messenger to a browser). |

Example command for locating an element:

```
// Android 

MobileElement button = driver.findElement(By.id("com.app:id/login_button"));
```

```
// iOS 

MobileElement input = driver.findElement(By.iOSClassChain("\*\*/XCUIElementTypeTextField[1]"));
```

**In summary, keep the following in mind**:

Appium is a powerful and flexible tool for automating mobile app tests. However, it has one major drawback: it’s generally considered the least stable among UI testing frameworks, which makes ongoing maintenance challenging.

Setting up an Appium environment can be time-consuming, often taking days rather than hours.

Since stability and speed are crucial for effective test automation, many companies, including T-Bank and Avito, opt for simpler, easier-to-manage tools. They choose tools such as Espresso/Kaspresso for Android and other specialized tools for iOS.

Currently, Espresso (and occasionally its Kotlin-based counterpart, Kaspresso) offers the most relevant toolset for learning mobile test automation. We’ll explore this in greater detail in Chapter 3.4.

In this project, you will write automated tests using Espresso to master the core concepts of mobile test automation and gain practical experience.

### 3.3. Appium Inspector

Appium Inspector is a tool that helps you explore the structure of mobile apps and find locators for UI elements, such as buttons and input fields.

Key elements:

- Viewing the UI element hierarchy to understand how screens are organized.
- Inspecting element properties, such as ID, class, and screen coordinates.
- Generating code snippets for common actions, such as clicks and text input.

**How to get started with Appium Inspector:**

1. Launch the Appium Server.
2. Open Appium Inspector (it’s included in Appium Desktop).
3. Set your session parameters (capabilities), including platform, OS version, and the path to your app.
4. Connect to a device/emulator.

**Sample capabilities for Android:**


```
{
  "platformName": "Android",
      "appium:deviceName": "Pixel 6",
      "appium:app": "/path/to/app.apk",
      "appium:automationName": "UiAutomator2"
}
```

| **Appium Inspector Features** | **Appium Inspector drawbacks** |
| :- | :- |
| - Visual element highlighting directly on screenshots;<br>- Support for various locators, including ID, XPath, and className;<br>- Automatic code templates for Java and Python.| - Requires Xcode setup and a signed app for iOS;<br>- Challenges with hybrid apps (WebView);<br>- Occasional instability with dynamic elements.|

**_Recommendation: always double-check locators in the Inspector before adding them to your tests. This helps cut down on false test failures._**

### 3.4. Espresso

Espresso is a framework developed by Google for writing UI tests that is integrated with the Android platform.

Espresso works best when:

- You’re testing a native Android app;
- You need fast, reliable test execution;
Your development environment is Android Studio.

**Key features:**

1. **Synchronization with the UI thread:**

Espresso automatically waits for animations and UI updates to finish, helping to prevent unreliable test results.

1. **Simple syntax:**

Espresso uses clear, chainable methods:

```
onView(withId(R.id.login_button))
    .perform(click())
    .check(matches(isDisplayed()));
```

1. **Idling Resources:**

This mechanism lets Espresso handle asynchronous operations such as loading data from the network.

**Key components of the framework::**

```
ViewMatchers: Finding elements by ID, text, class, and other attributes.

withId(R.id.element_id), withText("Submit")
```
```
ViewActions: Actions (click, text input, swipe).

click(), typeText("<user@test.com>"), swipeLeft()
```
```
ViewAssertions: Verifying element states.

matches(isDisplayed()), doesNotExist()
```

**Why do AQA engineers choose Espresso?**

- **Speed**: Espresso tests run significantly faster than tests built with Appium or UiAutomator.
- **Stability**: Built-in synchronization reduces flakiness and makes test results more reliable.
- **Deep integration**: Direct access to the app’s internal context and resources.

## Chapter 4

### 4.1. Getting Started with the Framework

General recommendations for completing project tasks:

- Use Java 21 for all code.
- Non-test components (helpers, functional methods, etc.) should be placed in the src folder.
- Tests go in the src/test folder.
- Use the SOLID, DRY, and KISS architectural principles. Design objects to minimize the need for future modifications and errors.
- All tests must have descriptive names and include clear documentation via appropriate annotations.
- Every assertion should include a clear, readable error message.
- You’re free to use either JUnit or TestNG as your Test Runner — whichever you prefer.

Now, it's time to gain hands-on experience with Espresso, Android's standard tool for automating mobile UI tests. You will learn how to interact with app elements and the virtual device itself.

By the end, you’ll have a working example of a mobile app testing framework from a white-box perspective.

### Task 1. Working with Espresso

Imagine that you are interning at a company that is developing a mobile app. The team has just finished the first working prototype. As you prepare for the next release, you are asked to test the app’s core functionality before its launch.

Your assignment is to write a suite of automated tests using Espresso to verify the following:

- Screen transitions work smoothly;
- The interface responds quickly to user input;
- The app handles device orientation changes correctly.

Before you begin, there are two steps to complete:

1. Install Android Studio and set up an emulator — you’ll use this for all of the following tasks.
2. Install Appium Inspector and check the application's selectors on the emulator.

**Steps to complete the task:**

1. Download the mobile app that you will be testing from the Materials folder, and then open it in Android Studio.
2. Implement the following tests in the specified classes:
    1. ExampleInstrumentedTest: Open the activity with a text input field. Enter some text. Confirm that the text appears. Then, change the screen orientation. Verify that the text is no longer visible. Finally, return the emulator to its original state.
    2. IntentTest: Check that you’re starting from the first activity. Open the second activity and confirm that you’re on it. Verify that the displayed text matches what’s expected.
    3. TransitionTest: Test all possible transitions between app screens. Some of these transitions should be triggered using the system "Back" button.
    4. Toast Test: Click the mail button in the bottom right corner of the screen and verify that the correct text pops up.
    5. ToolbarTest: Click the three-dot menu in the top right corner, and confirm that a dropdown menu with the "Settings" option appears. Then, click elsewhere on the screen to close the menu.

**Recommendations for completing the task:**

- Use Gradle to build and manage dependencies.
- Take some time to review the official Espresso documentation — it’s a valuable resource for this assignment: <https://developer.android.com/training/testing/espresso>.
- Skim through the app’s code to identify the button IDs you’ll need for your tests.
- Note that the project uses JUnit 4, so make sure your test syntax matches this version.

![package](misc/images/package.png)

---

💡 [Press here](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624) **to leave feedback on this project.** It's anonymous and will help our «School 21» team improve the learning experience. We recommend completing the survey immediately after finishing the project.