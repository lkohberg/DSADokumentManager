# DSA Dokument Manager (abandoned)

This project is an unfinished JavaFX application created as a school project at **HTL Steyr**.  
The goal was to build a small **DSA (Das Schwarze Auge) character document viewer / manager** that reads character data from a PDF and displays it in a GUI.

Development was stopped before the project reached a usable or polished state.

## What it was supposed to do
- Load a DSA character sheet (PDF)
- Extract basic character attributes (race, culture, stats, base values)
- Display the data in a JavaFX UI
- Allow quick modification of Lebensenergie (HP) via buttons (+1, +5, -1, -5)

## Current state
- JavaFX controller exists (`DSADokumentenController`)
- Character data is read via a `PDFService` (not included / incomplete)
- UI logic for displaying stats works in principle
- Lebensenergie can be increased or decreased in the UI
- Image loading is hardcoded to a local file path (non-portable)

The project is **not runnable out of the box**.

## Known issues / limitations
- Hardcoded absolute image path
- Missing or incomplete `PDFService`
- No error handling for missing PDF data
- No persistence (changes are not saved)
- UI and code structure were not cleaned up
- Project was abandoned before refactoring

## Tech stack
- Java
- JavaFX
- Basic PDF parsing (planned)

## Why this repository exists
This repository is kept mainly for:
- Reference
- Code snippets
- Documentation of past school projects
- Learning purposes

It is **not actively maintained**.
No license.  
Use at your own risk.
