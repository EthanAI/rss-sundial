Spring 2013
ICS314 - Software Engineering
Sundial Mini Software Life Cycle
Description
The task is to take a small problem and go through a shortened life cycle to
develop a software solution. The problem is not difficult, so focus on the
software development process.
Every member of a team shall submit the same deliverables and receive the
same grade.

Deliverables**• URL of a five minute video demonstration of the use of your system. Use a
screen capture system such as jing or screencast-o-matic (or if you are a
Mac user, Quicktime) to capture the demo with a voiceover narration. Show
typical usage as well as at least two cases where your system detects user
errors. Submit the URL of your video, not the video itself. The actual
video should be uploaded to YouTube, or cloud storage such as dropbox.com,
box.com, Google drive, … Make sure the URL you submit is accessible by
me and Phil (i.e., make the URL public).
• One page design document describing how the major sub-parts of your
system work together.
• Documented source code.
• One page document describing how you tested your system, and the results
(number of failures, for example). You must use JUnit test cases in
addition to whatever other testing you need. You must also have someone in
a different time zone and latitude test your system. Hint: you may need to
enlist a friend or relative in another part of the world.**

**Problem****The problem is to create a system to make highly customized horizontal
sundials. To do this you need to engineer a module to compute the angle at
which to draw an arbitrary hour line on a horizontal dial.
At a minimum, your user interface shall accept as input: latitude, longitude,
and date. You can make fancier user interfaces (accept city or airport names,
for example) if you like. As long as you meet the minimal user interface
requirement, you can make the interface as pretty as you want.
The output from your program should be an image of a gnomon and an image
of the horizontal dial with hour lines. Users should be able to print the images,
assemble the gnomon and horizontal dial, go outside, and successfully read
standard (or daylight saving) time from the sundial.
Sundials can be very accurate. For this assignment your sundial will take in
account latitude, longitude (distance from a time zone’s standard meridian),
and date. I would not be surprised if your sundial is accurate to within one
minute.
The algorithm is described below as increasingly more detailed steps.
Things to Watch Out For
• you will probably want to use a web-based geographic servers to obtain
latitude and longitude information. Information on the standard
meridians for worldwide time zones are easily found.
• latitudes should be to the nearest minute (1 minute of latitude is about
equal to 1.15 miles on the ground)
AM PM
1 2 3
4
5
6
gnomon - angle is equal to the north latitude
• each hour of time is equal to 15 degrees of arc, each minute of time is
equal to 15 minutes of arc (example: 2h 35m of time = 38 degrees 45
minutes of arc)
• you will probably have to convert degrees-minutes-seconds of arc to
fractional degrees (example: 38 degrees 45 minutes = 38.75 degrees)
• take some time to study the math library you will use, make sure that
the trigonometric functions and the formulas you use are compatible
(either both use degrees or both use radians). You may have to convert
from radians to degrees, or vice versa.
• Does your system work at the equator? What about at the poles? What
about the southern hemisphere? What about daylight saving time?
Make sure your system will create sundials for Germany’s latitudes and
longitudes in case someone you know tests there**

The Algorithm
It is common to be in a situation where you are developing a small critical
module defined by prose and formulas you probably don't fully understand.
Your concern, of course, in this situation is to make ensure your
implementation is consistent with respect to the specification, even if you don't
know the motivation behind the spec.
Initial algorithm. For a horizontal sundial the basic relationship to
determine the angle of an hour line with the gnomon (see diagram) is:
tan(d) = tan(t)**sin(phi)
where phi is the latitude of the place the sundial is located, d is the angle
which the hour line makes with the gnomon, and t is the time measured from
noon in degrees of arc.**

First refinement. Sundials made using the above formula display "local
apparent time", not "standard time". At the POST building we would want a
sundial that displays Hawaii Standard Time. The POST building latitude and
longitude is 21.297481, -157.81635. So, the hour lines need to be adjusted
according to the difference between POST’s longitude (about 158 degrees) and
the standard meridian for Hawaii Standard Time (150 degrees).
Standard Meridians
Newfoundland 52.5 W
Atlantic 60
Eastern 75
Central 90
Mountain 105
Pacific 120
Yukon 135
Alaska-Hawaii 150
Bering 165
Since POST is about 8 degrees west of the standard meridian, a POST sundial
records time about 32 minutes (1 degree of arc = 4 minutes of time) earlier
than Hawaii Standard Time. So to correct for this, the 11a hour line on a
horizontal sundial for POST should be calculated as the 10:28a line. You can
see some good examples at
http://www.cso.caltech.edu/outreach/log/NIGHT_DAY/exercises.htm using Hilo
as an example.

Second refinement. The earth's orbit is not a perfect circle - in fact the earth
moves faster when it is farthest from the sun (summer in the northern
hemisphere) and slower when it is closest the sun (winter in the northern
hemisphere) -- see
http://www.cso.caltech.edu/outreach/log/NIGHT_DAY/equation.htm if you are
interested in how it works. A sundial will be as much as 16 minutes "off"
standard time.
To correct for this, use the "Equation of Time" (commonly represented as an
analemma on sundials). For our purposes, the equation of time, EOT, is:
EOT = Standard Time - Sundial Time
Sundials are "slow" in January, February, March, April, July, and September.
They are "fast" in May, June, October, November, and December.
YOU can use the following approximation for the Equation of Time:
http://www.susdesign.com/popups/sunangle/eot.php

**Notes**