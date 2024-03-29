# AntiShadowPatch

## Info

**AntiShadowPatch** is a mod that aims to reimplement bugs that Mojang patched without any mention in the changelogs(shadow patched).

The extra features that won't be included in this mod will be included in [NotSoShadowExtras](https://modrinth.com/mod/notsoshadowextras) (like **updateSuppressionCrashFix** and
**disableLightRecalculation**)

## The list of features
* [StackOverflow update suppression](https://www.youtube.com/watch?v=Dtke-Co5HFM) **(patched in 22w11a)**
* [Graceful stackoverflow handling](https://bugs.mojang.com/browse/MC-248200) **(changed in 1.18-pre2)**
* Graceful OOM handling **(changed in 23w35a)**
* [CCE update suppression](https://www.youtube.com/watch?v=f4ty-PZcvrI) **(patched in 23w35a)**
* [Item Shadowing 1.17](https://www.youtube.com/watch?v=oz2u7YMPjF4) **(patched in 21w43a)**
* [Item Shadowing 1.18](https://www.youtube.com/watch?v=gLQP_qfkjoQ) **(patched in "unknown, needs testing")**
* [The infinite furnace xp bug](https://youtu.be/p5awe_hOp08?si=ptoHr59GWVnVhPdU&t=265) **(patched in 23w35a)**
* Floating redstone components on top of trapdoors **(patched in 23w35a)**
* [Trapdoor update skipping](https://www.youtube.com/watch?v=hZEOyZ3CEXY) **(patched in 1.20-pre2)**
* [Old Dragon Freezing](https://www.youtube.com/watch?v=kxHpyV95rB0) **(changed in 1.20-pre1)**
* [1.14 Chunk Save State](https://www.youtube.com/watch?v=uw7vEGhKoH8) **(patched in 19w35a)**
* [Wither Invulnerable Armor Stands](https://www.youtube.com/watch?v=Qjtqd9EjvaA) **(patched in 1.20.2 pre-4)**
* [Understacked Items](https://mcdf.wiki.gg/wiki/Java_Edition:Understacked_Items) **(removed in 16w32a)**
* * This feature is experimental and can break something. If you find any game breaking issues, please report them on the GitHub repo.
* * Understacked items behave differently than on older versions, that's due to a lot of code changes. I will try to make them as close as possible to how they were.
* * There's currently no known way to obtain them in survival, however, you can use [NotSoShadowExtras](https://modrinth.com/mod/notsoshadowextras) to /give them.


You can configure every setting individually in antishadowpatch_config.json
