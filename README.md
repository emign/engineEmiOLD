![](https://github.com/emign/engineEmi/workflows/DocumentationBuild/badge.svg)
![](https://github.com/emign/engineEmi/workflows/jsWebBuild/badge.svg)
![](https://github.com/emign/engineEmi/workflows/jvmBuild/badge.svg)
![](https://github.com/emign/engineEmi/workflows/macosnativebuild/badge.svg)
![](https://img.shields.io/github/v/release/emign/engineEmi?labelColor=262B30)


# Changelog

## 0.15
- engine.run() nimmt nun width und height als optionale Parameter um das GameWindow in der Größe anzupassen
- Keyboard und Mouse-Input nun möglich
- WorldView zentriert

## 0.14
### Added
- Korge 1.4.2 Support
- MacOSX64 native target

## Changed
- Engine ist jetzt Objekt und kein Singleton mehr. (geänderter engine-Start s. Docs)
- Dokka Generierung umbenannt und an task Management angepasst.

## 0.13c
### Added
- Support für github actions
- github badges für jvm und js build, doc und versionsnummer
- fun register() erweitert um Collections und Arrays im Gesamten anzumelden

### Fixed
- Breite und Höhe kann nun korrekt angezeigt werden `Engine.view.height`

## 0.12 alpha 2019-11-29
### Added
- Kotlin 1.3.60 support
- Korge 1.40 support
- Property skalierung bei Bild hinzuge`fügt. Nun kann ein Bild einfach auf die korrekte Größe skaliert werden (0.5 -> 50%, ...)
- `Image` Klasse bei den Bodies. Zeigt Bilder anstatt Geometrische Formen an.
- Doku überarbeitet
- `animate(animationRoutine) Funktion hinzugefügt, um Objekte auch ohne Erzeugung von Subklassen zu animieren.

### Fixed
- Skalierung der `CanvasElements`und `Bodies`nun automatisch. Keine ausgefragsten Ecken mehr bei Geraden

## 0.11 alpha 2019-11-25 
### Added
- Property skalierung bei Bild hinzugefügt. Nun kann ein Bild einfach auf die korrekte Größe skaliert werden (0.5 -> 50%, ...)
- `Image` Klasse bei den Bodies. Zeigt Bilder anstatt Geometrische Formen an.

### Issues
- Spawning multiple `Images` throws an exception (see [https://github.com/korlibs/korge/issues/45#issuecomment-558140737](https://github.com/korlibs/korge/issues/45#issuecomment-558140737) )

## 0.1 alpha 2019-11-22 
Testbranch umbenannt in dev. Die Implementierung von box2d und der korge engine wird nun priorisiert.
### Added
- Alle CanvasElements besitzen nun deutsche Namen und Propertiebezeichnungen, was die Abgrenzung zu den Bodies erleichtert
- Alle CanvasElements und Bodies können nun mit Zahlenwerten vom Typ `Number` initialisiert werden (kein Double, Int, Float mehr notwendig.) 

